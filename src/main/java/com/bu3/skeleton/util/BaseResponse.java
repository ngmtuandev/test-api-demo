package com.bu3.skeleton.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Builder
public class BaseResponse<T> {

    private String code;

    private Integer status;

    private T data;

    private String message;

    private TimeUnit responseTime;

}
