package com.bu3.skeleton.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Pageable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Builder
public class BasesResponse<T> {

    private String code;

    private Integer status;

    private List<T> data;

    private Pageable meta;

    private String message;

    private TimeUnit responseTime;

}
