package com.myApp.hangar.builder;

import com.myApp.model.Hangar;
import com.myApp.model.Minified;

public class MinifiedHangarDtoBuilder {

    private Minified minified;

    public MinifiedHangarDtoBuilder(Hangar hangar) {
        this.minified = new Minified();

        this.minified.setId(hangar.getId());
        this.minified.setName(hangar.getName());
    }

    public Minified getBasicDataHangarDto() {
        return minified;
    }
}
