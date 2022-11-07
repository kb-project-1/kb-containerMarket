package com.kb1.containerMarket.repository.domain.admin;

import com.kb1.containerMarket.web.dto.admin.ProductMstRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMst {

    private int id;
    private String pdt_name;
    private int category_id;
    private int pdt_price;
    private String pdt_simple_info;
    private String pdt_detail_info;

    public ProductMstRespDto toDto() {
        return ProductMstRespDto.builder()
                .productId(id)
                .productName(pdt_name)
                .categoryId(category_id)
                .productPrice(pdt_price)
                .simpleInfo(pdt_simple_info)
                .detailInfo(pdt_detail_info)
                .build();
    }
}
