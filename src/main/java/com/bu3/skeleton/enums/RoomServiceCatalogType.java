package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomServiceCatalogType {
    MAIN("Dịch vụ phòng"), AMENITY("Dịch vụ bổ sung");
    private final String value;
    RoomServiceCatalogType(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
