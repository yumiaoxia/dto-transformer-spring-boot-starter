package com.itsherman.dtoassembler.handler.inter;

import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.core.InterViewPropertyDefinition;
import com.itsherman.dtoassembler.core.ModelPropertyDefinition;
import com.itsherman.dtoassembler.exception.DtoPrepareException;
import com.itsherman.dtoassembler.handler.DtoViewRegister;
import com.itsherman.dtoassembler.pool.DtoViewDefinitionPool;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DtoInterViewRegister implements DtoViewRegister {

    @Resource
    private DtoViewDefinitionPool dtoViewDefinitionPool;

    public void register(DtoModelDefinition md) {
        Class<?> dtoInter = md.getDtoClass();
        Set<Class<?>> parserClasses = new HashSet<>();
        Set<Method> dtoMethods = new HashSet<>();
        for (ModelPropertyDefinition mpd : md.getMpds()) {
            DtoInterPropertyDefinition dipd = (DtoInterPropertyDefinition) mpd;
            parserClasses.addAll(dipd.getParserClass());
            dtoMethods.add(dipd.getDtoMethod());
        }
        for (Class<?> parserClass : parserClasses) {
            for (ModelPropertyDefinition mpd : md.getMpds()) {
                DtoInterPropertyDefinition dipd = (DtoInterPropertyDefinition) mpd;
                if (dipd.getParserClass().contains(parserClass)) {
                    InterViewPropertyDefinition ivpd = new InterViewPropertyDefinition();
                    ivpd.getDtoMethods().add(dipd.getDtoMethod());
                    String[] referenceDtoMethodNames = dipd.getReferenceDtoMethodNames();
                    if (referenceDtoMethodNames != null && referenceDtoMethodNames.length > 0) {
                        for (String referenceDtoMethodName : referenceDtoMethodNames) {
                            Optional<Method> resultMethodOp = dtoMethods.stream().filter(dtoMethod -> dtoMethod.getName().equals(referenceDtoMethodName)).findFirst();
                            if (resultMethodOp.isPresent()) {
                                ivpd.getDtoMethods().add(resultMethodOp.get());
                            } else {
                                throw new DtoPrepareException(String.format("%s can not found reference dtoMethod(%s) on compute method(%s)", md.getDtoClass().getSimpleName(), referenceDtoMethodName, dipd.getDtoMethod().getName()));
                            }
                        }
                    }
                    dtoViewDefinitionPool.addViewProperty(parserClass, md, ivpd);
                }
            }

        }
    }
}
