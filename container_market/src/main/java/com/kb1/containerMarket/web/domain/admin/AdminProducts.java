package com.kb1.containerMarket.web.domain.admin;

import com.kb1.containerMarket.web.dto.admin.AdminProductsRespDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminProducts {
    private int id;
    private String category_name;
    private String pdt_name;
    private int pdt_price;
    private int product_total_count;

    public AdminProductsRespDto toDto() {
        return AdminProductsRespDto.builder()
                .productId(id)
                .categoryName(category_name)
                .productName(pdt_name)
                .productPrice(pdt_price)
                .productTotalCount(product_total_count)
                .build();
    }
}
