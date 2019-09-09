package com.myApp.hangar.builder;

import com.myApp.hangar.dto.BasicDataHangarDto;
import com.myApp.model.Hangar;

public class BasicDataHangarDtoBuilder {

    private BasicDataHangarDto basicDataHangarDto;

    public BasicDataHangarDtoBuilder(Hangar hangar) {
        this.basicDataHangarDto = new BasicDataHangarDto();

        this.basicDataHangarDto.setId(hangar.getId());
        this.basicDataHangarDto.setName(hangar.getName());
    }

    public BasicDataHangarDto getBasicDataHangarDto() {
        return basicDataHangarDto;
    }
}
