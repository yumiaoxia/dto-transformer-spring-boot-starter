package com.itsherman.dtoassembler.core;

import java.util.HashSet;
import java.util.Set;

public class DtoViewDefinition {

    private DtoModelDefinition md;

    private Set<ViewPropertyDefinition> vpds = new HashSet<>();

    public DtoModelDefinition getMd() {
        return md;
    }

    public void setMd(DtoModelDefinition md) {
        this.md = md;
    }

    public Set<ViewPropertyDefinition> getVpds() {
        return vpds;
    }

    public void setVpds(Set<ViewPropertyDefinition> vpds) {
        this.vpds = vpds;
    }
}
