package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeHotelPolicy {
    FREE("Hủy miễn phí", "Free");
    private final String valueVN;
    private final String valueEN;
    TypeHotelPolicy(String valueVN, String valueEN){
        this.valueVN = valueVN;
        this.valueEN = valueEN;
    }

    @JsonValue
    public String getValueVN(){
        return this.valueVN;
    }
    @JsonValue
    public String getValueEN(){
        return this.valueEN;
    }
}
