package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HotelServiceCatalogType {
    LOCATION("location"), AMENITY("amenity");
    private final String value;
    HotelServiceCatalogType(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
