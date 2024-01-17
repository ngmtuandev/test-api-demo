package com.bu3.skeleton.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Builder
public class PageableResponse {

    private int totalPage;

    private Pageable meta;
}
