package com.itsherman.dtoassembler.handler.inter;


import com.itsherman.dtoassembler.constants.Commonconstants;
import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.exception.DtoPrepareException;
import com.itsherman.dtoassembler.handler.DtoModelRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InterDtoModelRegister extends DtoModelRegister {
    private static final Logger log = LoggerFactory.getLogger(InterDtoModelRegister.class);

    @Resource
    private DtoInterfacePropertyValueHandler dtoInterfacePropertyValueHandler;

    @Resource
    private DtoInterfacePropertySourceClassHandler dtoInterfacePropertySourceClassHandler;

    @Resource
    private DtoInterModelPropertyDtoViewHandler dtoInterModelPropertyDtoViewHandler;

    @Resource
    private DtoInterPropertyParserClassHandler dtoInterPropertyParserClassHandler;

    @Override
    public void doRegister(DtoModelDefinition md) {
        Method[] methods = md.getDtoClass().getMethods();
        List<Method> dtoMethods = Arrays.stream(methods).filter(method ->
                method.getName().startsWith(Commonconstants.GETTER_PREFIX) && !Modifier.isStatic(method.getModifiers()))
                .collect(Collectors.toList());
        for (Method dtoMethod : dtoMethods) {
            DtoInterPropertyDefinition ipd = new DtoInterPropertyDefinition();
            ipd.setMd(md);
            ipd.setDtoMethod(dtoMethod);
            try {
                dtoInterModelPropertyDtoViewHandler.handle(ipd);
                dtoInterfacePropertyValueHandler.handle(ipd);
                dtoInterfacePropertySourceClassHandler.handle(ipd);
                dtoInterPropertyParserClassHandler.handle(ipd);
            } catch (DtoPrepareException e) {
                log.error(e.getMessage(), e);
                continue;
            }
            md.getMpds().add(ipd);
        }
    }
}
