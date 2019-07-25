package com.myApp.hangar.builder;

import com.myApp.hangar.dto.HangarDto;
import com.myApp.hangar.model.Hangar;

public class HangarDtoBuilder {

    private HangarDto hangarDto;

    public HangarDtoBuilder(Hangar hangar) {
        this.hangarDto = new HangarDto();

        this.hangarDto.setId(hangar.getId());
        this.hangarDto.setName(hangar.getName());
        this.hangarDto.setAddress(hangar.getAddress());
    }

    public HangarDto getHangarDto() {
        return hangarDto;
    }


}
