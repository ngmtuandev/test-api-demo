package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivityHistoryType {
    ACCEPTED("Xác nhận"), CANCEL("Hủy");
    private final String value;
    ActivityHistoryType(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
