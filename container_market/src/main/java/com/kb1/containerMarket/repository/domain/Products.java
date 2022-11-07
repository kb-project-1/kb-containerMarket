package com.kb1.containerMarket.repository.domain;

import com.kb1.containerMarket.web.dto.ProductsRespDto;

public class Products {

    private int id;
    private String pdt_name;
    private int pdt_price;
    private String save_name;
    private int product_total_count;

    public ProductsRespDto toDto() {
        return ProductsRespDto.builder()
                .productId(id)
                .productName(pdt_name)
                .productPrice(pdt_price)
                .mainImg(save_name)
                .productTotalCount(product_total_count)
                .build();
    }
}
