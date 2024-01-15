package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HotelStatus {
    ACTIVE("active"), IN_ACTIVE("in active");
    private final String value;
    HotelStatus(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
