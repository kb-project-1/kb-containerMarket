package com.kb1.containerMarket.web.dto.admin;

import com.kb1.containerMarket.repository.domain.Product;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ProductRegisterReqDto {
    private int category;
    private String name;
    @Min(value = 100, message = "최소 가격은 100원입니다.")
    private int price;
    private String simpleInfo;
    private String detailInfo;

    public Product toEntity() {
        return Product
                .builder()
                .category_id(category)
                .pdt_name(name)
                .pdt_price(price)
                .pdt_simple_info(simpleInfo)
                .pdt_detail_info(detailInfo)
                .build();
    }
}
