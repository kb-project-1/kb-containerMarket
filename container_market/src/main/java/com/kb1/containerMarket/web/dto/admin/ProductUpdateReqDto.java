package com.kb1.containerMarket.web.dto.admin;

import com.kb1.containerMarket.web.domain.admin.ProductMst;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Builder
@Data
public class ProductUpdateReqDto {
    private int id;
    private String name;
    private int category;
    @Min(value = 100, message = "최소 가격은 100원입니다.")
    private int price;
    private String simpleInfo;
    private String detailInfo;

    public ProductMst toEntity() {
        return ProductMst.builder()
                .pdt_name(name)
                .category_id(category)
                .pdt_price(price)
                .pdt_simple_info(simpleInfo)
                .pdt_detail_info(detailInfo)
                .id(id)
                .build();
    }
}
