package com.bu3.skeleton.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HotelRequest {

    private Double rating;

    private BigDecimal priceBase;

    private String parentId;

    private String languageCode;

    private String address;

}
