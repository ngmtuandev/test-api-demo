package com.bu3.skeleton.dto.request.hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class HotelRequest {

    private Double rating;

    private BigDecimal priceBase;

    private String parentId;

    private String languageCode;

    private String address;

}
