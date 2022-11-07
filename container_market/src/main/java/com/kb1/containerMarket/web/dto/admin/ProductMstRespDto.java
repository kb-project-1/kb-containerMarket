package com.kb1.containerMarket.web.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductMstRespDto {
    private int productId;
    private String productName;
    private int categoryId;
    private int productPrice;
    private String simpleInfo;
    private String detailInfo;
}
