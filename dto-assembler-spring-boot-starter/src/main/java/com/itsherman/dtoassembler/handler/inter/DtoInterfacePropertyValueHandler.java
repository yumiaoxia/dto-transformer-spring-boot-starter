package com.itsherman.dtoassembler.handler.inter;


import com.itsherman.dtoassembler.annotations.DtoProperty;
import com.itsherman.dtoassembler.constants.Commonconstants;
import com.itsherman.dtoassembler.core.DtoInterPropertyDefinition;

import java.lang.reflect.Method;

public class DtoInterfacePropertyValueHandler {

    public void handle(DtoInterPropertyDefinition ipd) {
        Method dtoMethod = ipd.getDtoMethod();
        String expectDtoFieldName = dtoMethod.getName().replace(Commonconstants.GETTER_PREFIX, "");
        expectDtoFieldName = expectDtoFieldName.substring(0, 1).toLowerCase() + expectDtoFieldName.substring(1);
        DtoProperty dtoProperty = dtoMethod.getAnnotation(DtoProperty.class);
        if (dtoProperty != null) {
            String value = dtoProperty.value();
            ipd.setValue(value.equals("") ? expectDtoFieldName : value);
        } else {
            ipd.setValue(expectDtoFieldName);
        }
    }
}
