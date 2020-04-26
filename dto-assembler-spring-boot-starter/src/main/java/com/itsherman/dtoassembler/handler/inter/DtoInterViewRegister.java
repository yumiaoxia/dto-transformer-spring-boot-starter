package com.itsherman.dtoassembler.handler.inter;

import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.core.InterViewPropertyDefinition;
import com.itsherman.dtoassembler.core.ModelPropertyDefinition;
import com.itsherman.dtoassembler.handler.DtoViewRegister;
import com.itsherman.dtoassembler.pool.DtoViewDefinitionPool;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class DtoInterViewRegister implements DtoViewRegister {

    @Resource
    private DtoViewDefinitionPool dtoViewDefinitionPool;

    public void register(DtoModelDefinition md) {
        Class<?> dtoInter = md.getDtoClass();
        Set<Class<?>> parserClasses = new HashSet<>();
        for (ModelPropertyDefinition mpd : md.getMpds()) {
            parserClasses.addAll(((DtoInterPropertyDefinition) mpd).getParserClass());
        }
        for (Class<?> parserClass : parserClasses) {
            for (ModelPropertyDefinition mpd : md.getMpds()) {
                DtoInterPropertyDefinition dipd = (DtoInterPropertyDefinition) mpd;
                if (dipd.getParserClass().contains(parserClass)) {
                    InterViewPropertyDefinition ivpd = new InterViewPropertyDefinition();
                    ivpd.setDtoMethod(dipd.getDtoMethod());
                    dtoViewDefinitionPool.addViewProperty(parserClass, md, ivpd);
                }
            }

        }
    }
}
