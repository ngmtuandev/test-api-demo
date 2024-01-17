package com.bu3.skeleton.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class BaseResponse<T> {

    private String code;

    private Integer status;

    private T data;

    private String message;

    private long responseTime;

}
