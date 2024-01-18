package com.bu3.skeleton.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class BasesResponse extends BaseResponse {
    private PageableResponse meta;
}
