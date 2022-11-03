package com.kb1.containerMarket.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductsRespDto {
    private int productId;
    private String productName;
    private int productPrice;
    private String mainImg;
    private int productTotalCount;
}
