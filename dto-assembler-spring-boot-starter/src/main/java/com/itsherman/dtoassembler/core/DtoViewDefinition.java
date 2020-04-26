package com.itsherman.dtoassembler.core;

import java.util.ArrayList;
import java.util.List;

public class DtoViewDefinition {

    private DtoModelDefinition md;

    private List<ViewPropertyDefinition> vpds = new ArrayList<>();

    public DtoModelDefinition getMd() {
        return md;
    }

    public void setMd(DtoModelDefinition md) {
        this.md = md;
    }

    public List<ViewPropertyDefinition> getVpds() {
        return vpds;
    }

    public void setVpds(List<ViewPropertyDefinition> vpds) {
        this.vpds = vpds;
    }
}
