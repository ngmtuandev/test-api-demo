package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomStatus {
    ACTIVE("active"), IN_ACTIVE("in active"), DELETED("deleted");
    private final String value;
    RoomStatus(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
