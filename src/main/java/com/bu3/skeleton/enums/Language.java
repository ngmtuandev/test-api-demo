package com.bu3.skeleton.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    VI("Vietnamese"), EN("English");
    private final String value;
    Language(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return this.value;
    }
}
