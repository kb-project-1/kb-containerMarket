package com.kb1.containerMarket.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductCartRespDto {
    private String img;
    private String name;
    private int amount;
    private int price;
}
