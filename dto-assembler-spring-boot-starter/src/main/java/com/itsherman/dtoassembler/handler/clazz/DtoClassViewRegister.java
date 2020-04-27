package com.itsherman.dtoassembler.handler.clazz;


import com.itsherman.dtoassembler.core.ClassViewPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoClassPropertyDefinition;
import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.core.ModelPropertyDefinition;
import com.itsherman.dtoassembler.handler.DtoViewRegister;
import com.itsherman.dtoassembler.pool.DtoViewDefinitionPool;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DtoClassViewRegister implements DtoViewRegister {

    @Resource
    private DtoViewDefinitionPool dtoViewDefinitionPool;

    public void register(DtoModelDefinition md) {
        Class<?> dtoClass = md.getDtoClass();

        List<Field> fields = new ArrayList<>();
        Set<Class<?>> parserClasses = new HashSet<>();
        for (ModelPropertyDefinition mpd : md.getMpds()) {
            DtoClassPropertyDefinition dcpd = (DtoClassPropertyDefinition) mpd;
            parserClasses.addAll(dcpd.getParserClass());
            Field dtoField = dcpd.getDtoField();
            if (dtoField != null) {
                fields.add(dtoField);
            }
        }

        for (Class<?> parserClass : parserClasses) {
            ClassViewPropertyDefinition cvpd = new ClassViewPropertyDefinition();
            for (ModelPropertyDefinition mpd : md.getMpds()) {
                DtoClassPropertyDefinition dcpd = (DtoClassPropertyDefinition) mpd;
                Field dtoField = dcpd.getDtoField();
                String[] referenceFieldNames = dcpd.getReferenceFieldNames();
                if (dtoField != null) {
                    cvpd.getFields().add(dtoField);
                } else if (referenceFieldNames != null && dcpd.getParserClass().contains(parserClass)) {
                    for (Field field : fields) {
                        for (String referenceFieldName : referenceFieldNames) {
                            if (field.getName().equals(referenceFieldName)) {
                                cvpd.getFields().add(field);
                            }
                        }
                    }
                }
            }
            dtoViewDefinitionPool.addViewProperty(parserClass, md, cvpd);
        }

    }
}
