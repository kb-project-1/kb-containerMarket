package com.kb1.containerMarket.web.domain.admin;

import com.kb1.containerMarket.web.dto.admin.AdminProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminProduct {
    private int id;
    private int category;
    private String pdt_name;
    private int pdt_price;
    private int product_total_count;

    public AdminProductResponseDto toDto() {
        return AdminProductResponseDto.builder()
                .productId(id)
                .category(category)
                .productName(pdt_name)
                .productPrice(pdt_price)
                .productTotalCount(product_total_count)
                .build();
    }
}
