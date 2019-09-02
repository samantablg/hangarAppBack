package com.myApp.hangar.builder;

import com.myApp.hangar.dto.HangarDto;
import com.myApp.hangar.model.Hangar;

public class DtoBuilder {

    private HangarDto hangarDto;

    public DtoBuilder(Hangar hangar) {
        this.hangarDto = new HangarDto();

        this.hangarDto.setId(hangar.getId());
        this.hangarDto.setName(hangar.getName());
        this.hangarDto.setAddress(hangar.getAddress());
        this.hangarDto.setOwner(hangar.getOwner());
        this.hangarDto.setEmail(hangar.getEmail());
        this.hangarDto.setPhone(hangar.getPhone());
        this.hangarDto.setState(hangar.isState());
    }

    public HangarDto getHangarDto() {
        return hangarDto;
    }

}
