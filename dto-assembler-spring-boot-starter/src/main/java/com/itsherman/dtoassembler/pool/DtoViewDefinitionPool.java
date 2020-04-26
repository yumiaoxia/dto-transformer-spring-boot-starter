package com.itsherman.dtoassembler.pool;

import com.itsherman.dtoassembler.core.DtoModelDefinition;
import com.itsherman.dtoassembler.core.DtoViewDefinition;
import com.itsherman.dtoassembler.core.ViewPropertyDefinition;

import java.util.HashMap;
import java.util.Map;

public class DtoViewDefinitionPool {

    private Map<Class<?>, Map<Class<?>, DtoViewDefinition>> viewMap = new HashMap<>();

    public void addViewProperty(Class<?> viewClass, DtoModelDefinition md, ViewPropertyDefinition viewPropertyDefinition) {
        Map<Class<?>, DtoViewDefinition> dvdMap = viewMap.get(md.getDtoClass());
        if (dvdMap != null) {
            DtoViewDefinition dvd = dvdMap.get(viewClass);
            if (dvd != null) {
                dvd.getVpds().add(viewPropertyDefinition);
            } else {
                dvd = new DtoViewDefinition();
                dvd.setMd(md);
                dvd.getVpds().add(viewPropertyDefinition);
                dvdMap.put(viewClass, dvd);

            }
        } else {
            dvdMap = new HashMap<>();
            DtoViewDefinition dvd = new DtoViewDefinition();
            dvd.setMd(md);
            dvd.getVpds().add(viewPropertyDefinition);
            dvdMap.put(viewClass, dvd);
            viewMap.put(md.getDtoClass(), dvdMap);
        }
    }

    public DtoViewDefinition getViewDefinition(Class<?> dtoClass, Class<?> viewCLass) {
        Map<Class<?>, DtoViewDefinition> dvdMap = viewMap.get(dtoClass);
        if (dvdMap != null) {
            return dvdMap.get(viewCLass);
        }
        return null;
    }


}
