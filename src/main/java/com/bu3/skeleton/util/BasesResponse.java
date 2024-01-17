package com.bu3.skeleton.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class BasesResponse<T> {

    private String code;

    private Integer status;

    private List<T> data;

    private PageableResponse meta;

    private String message;

    private long responseTime;

}
