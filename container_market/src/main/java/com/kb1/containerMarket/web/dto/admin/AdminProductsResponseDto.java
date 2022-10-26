package com.kb1.containerMarket.web.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminProductsResponseDto {
    private int productId;
    private String categoryName;
    private String productName;
    private int productPrice;
    private int productTotalCount;
}
