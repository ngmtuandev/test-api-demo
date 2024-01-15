package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {
    PAID("Đã thanh toán"), UN_PAID("Chưa thanh toán");
    private final String value;
    PaymentStatus(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
