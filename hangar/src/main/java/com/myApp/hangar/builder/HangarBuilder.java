package com.myApp.hangar.builder;

import com.myApp.hangar.dto.HangarDto;
import com.myApp.model.Hangar;

public class HangarBuilder {

    private Hangar hangar;

    public HangarBuilder(HangarDto hangarDto) {
        this.hangar = new Hangar();

        this.hangar.setId(hangarDto.getId());
        this.hangar.setName(hangarDto.getName());
        this.hangar.setAddress(hangarDto.getAddress());
        this.hangar.setOwner(hangarDto.getOwner());
        this.hangar.setEmail(hangarDto.getEmail());
        this.hangar.setPhone(hangarDto.getPhone());
        this.hangar.setState(hangarDto.isState());
    }

    public Hangar getHangar() {
        return hangar;
    }
}
