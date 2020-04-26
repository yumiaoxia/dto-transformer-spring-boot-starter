package com.itsherman.dtoassembler.handler.clazz;


import com.itsherman.dtoassembler.constants.Commonconstants;
import com.itsherman.dtoassembler.core.ClassViewPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.core.ModelPropertyDefinition;
import com.itsherman.dtoassembler.handler.DtoViewRegister;
import com.itsherman.dtoassembler.pool.DtoViewDefinitionPool;
import com.itsherman.dtoassembler.utils.ReflectUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoClassViewRegister implements DtoViewRegister {

    @Resource
    private DtoViewDefinitionPool dtoViewDefinitionPool;

    public void register(DtoModelDefinition md) {
        Class<?> dtoClass = md.getDtoClass();
        List<Field> fields = ReflectUtils.getAllNonStaticFields(dtoClass);
        List<Method> dtoReadMethods = Arrays.stream(dtoClass.getMethods()).filter(method ->
                method.getName().startsWith(Commonconstants.GETTER_PREFIX) && !Modifier.isStatic(method.getModifiers()))
                .collect(Collectors.toList());

        Set<Class<?>> parserClasses = new HashSet<>();
        for (ModelPropertyDefinition mpd : md.getMpds()) {
            DtoClassPropertyDefinition dcpd = (DtoClassPropertyDefinition) mpd;
            parserClasses.addAll(dcpd.getParserClass());
        }

        for (Class<?> parserClass : parserClasses) {
            ClassViewPropertyDefinition cvpd = new ClassViewPropertyDefinition();
            for (ModelPropertyDefinition mpd : md.getMpds()) {
                DtoClassPropertyDefinition dcpd = (DtoClassPropertyDefinition) mpd;
                Field dtoField = dcpd.getDtoField();
                String[] referenceFieldNames = dcpd.getReferenceFieldNames();
                if (referenceFieldNames != null && dtoField == null) {
                    for (Field field : fields) {
                        for (String referenceFieldName : referenceFieldNames) {
                            if (field.getName().equals(referenceFieldName)) {
                                cvpd.getFields().add(field);
                            }
                        }
                    }
                } else if (dcpd.getParserClass().contains(parserClass)) {
                    cvpd.getFields().add(dcpd.getDtoField());
                }
                dtoViewDefinitionPool.addViewProperty(parserClass, md, cvpd);
            }
        }

    }
}
