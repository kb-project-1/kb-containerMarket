package com.kb1.containerMarket.web.dto.admin;

import com.kb1.containerMarket.web.domain.admin.ProductDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDtlRegisterDto {
    private int pdtId;
    private int pdtSize;
    private String pdtColor;
    private int pdtStock;

    public ProductDetail toEntity() {
        return ProductDetail.builder()
                .pdt_id(pdtId)
                .size_id(pdtSize)
                .pdt_color(pdtColor)
                .pdt_stock(pdtStock)
                .build();
    }

}
