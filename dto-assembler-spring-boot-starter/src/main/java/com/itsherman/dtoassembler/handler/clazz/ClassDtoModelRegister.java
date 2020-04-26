package com.itsherman.dtoassembler.handler.clazz;


import com.itsherman.dtoassembler.constants.Commonconstants;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.core.ModelPropertyDefinition;
import com.itsherman.dtoassembler.handler.DtoModelRegister;
import com.itsherman.dtoassembler.utils.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClassDtoModelRegister extends DtoModelRegister {

    private static final Logger log = LoggerFactory.getLogger(ClassDtoModelRegister.class);

    @Resource
    private DtoClassModelPropertyDtoViewHandler dtoClassModelPropertyDtoViewHandler;

    @Resource
    private DtoClassPropertyValueHandler dtoClassPropertyValueHandler;

    @Resource
    private DtoClassPropertySourceClassHandler dtoClassPropertySourceClassHandler;

    @Resource
    private DtoClassPropertyParserClassHandler dtoClassPropertyParserClassHandler;

    @Override
    public void doRegister(DtoModelDefinition md) {
        Class<?> dtoClass = md.getDtoClass();
        List<Field> fields = ReflectUtils.getAllNonStaticFields(md.getDtoClass());
        List<Method> methods = Arrays.stream(dtoClass.getMethods())
                .filter(method -> !Modifier.isStatic(method.getModifiers()))
                .collect(Collectors.toList());

        List<Method> readMethods = methods.stream().filter(method -> method.getName().startsWith(Commonconstants.GETTER_PREFIX)).filter(method -> !method.getDeclaringClass().equals(Object.class)).collect(Collectors.toList());
        List<Method> writeMethods = methods.stream().filter(method -> method.getName().startsWith(Commonconstants.SETTER_PREFIX)).collect(Collectors.toList());
        for (Method writeMethod : writeMethods) {
            String expectFieldName = writeMethod.getName().replace(Commonconstants.SETTER_PREFIX, "");
            expectFieldName = expectFieldName.substring(0, 1).toLowerCase() + expectFieldName.substring(1);
            DtoClassPropertyDefinition dcpd = new DtoClassPropertyDefinition();
            dcpd.setMd(md);
            dcpd.setDtoWriteMethod(writeMethod);
            String finalExpectFieldName = expectFieldName;
            Optional<Field> fieldOp = fields.stream().filter(field -> field.getName().equals(finalExpectFieldName)).findFirst();
            fieldOp.ifPresent(dcpd::setDtoField);
            String expectReadMethodName = writeMethod.getName().replace(Commonconstants.SETTER_PREFIX, Commonconstants.GETTER_PREFIX);
            dcpd.setExpectFieldName(expectFieldName.substring(0, 1).toLowerCase() + expectFieldName.substring(1));
            Optional<Method> readMethodOp = readMethods.stream().filter(readMethod -> readMethod.getName().equals(expectReadMethodName)).findFirst();
            readMethodOp.ifPresent(dcpd::setDtoReadMethod);
            md.getMpds().add(dcpd);
        }

        for (Field field : fields) {
            List<ModelPropertyDefinition> mpds = md.getMpds();
            boolean flag = false;
            for (ModelPropertyDefinition mpd : mpds) {
                DtoClassPropertyDefinition dcpd = (DtoClassPropertyDefinition) mpd;
                if (dcpd.getDtoField().equals(field)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                DtoClassPropertyDefinition dcpd = new DtoClassPropertyDefinition();
                dcpd.setMd(md);
                dcpd.setDtoField(field);
                dcpd.setExpectFieldName(field.getName());
                String expectDtoReadMethodName = Commonconstants.GETTER_PREFIX + field.getName().substring(0, 1) + field.getName().substring(1);
                Optional<Method> readMethodOp = readMethods.stream().filter(readMethod -> readMethod.getName().equals(expectDtoReadMethodName)).findFirst();
                readMethodOp.ifPresent(dcpd::setDtoReadMethod);
                md.getMpds().add(dcpd);
            }
        }
        for (Method readMethod : readMethods) {
            List<ModelPropertyDefinition> mpds = md.getMpds();
            boolean flag = false;
            for (ModelPropertyDefinition mpd : mpds) {
                DtoClassPropertyDefinition dcpd = (DtoClassPropertyDefinition) mpd;
                if (dcpd.getDtoReadMethod().equals(readMethod)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                DtoClassPropertyDefinition dcpd = new DtoClassPropertyDefinition();
                dcpd.setMd(md);
                dcpd.setDtoReadMethod(readMethod);
                md.getMpds().add(dcpd);
            }
        }

        for (ModelPropertyDefinition mpd : md.getMpds()) {
            DtoClassPropertyDefinition dcpd = (DtoClassPropertyDefinition) mpd;
            dtoClassModelPropertyDtoViewHandler.handle(dcpd);
            dtoClassPropertyValueHandler.handle(dcpd);
            dtoClassPropertySourceClassHandler.handle(dcpd);
            dtoClassPropertyParserClassHandler.handle(dcpd);
        }
    }

}
