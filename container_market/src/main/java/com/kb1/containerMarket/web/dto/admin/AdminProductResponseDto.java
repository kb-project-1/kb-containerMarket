package com.kb1.containerMarket.web.dto.admin;

import com.kb1.containerMarket.web.domain.Product;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminProductResponseDto {
    private int productId;
    private String categoryName;
    private String productName;
    private int productPrice;
    private int productTotalCount;
}
