package com.kb1.containerMarket.web.domain.admin;

import com.kb1.containerMarket.web.dto.admin.ProductOptionRespDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOption {
    int pdt_id;
    String category_name;
    String pdt_name;

    public ProductOptionRespDto toDto() {
        return ProductOptionRespDto.builder()
                .pdtId(pdt_id)
                .category(category_name)
                .pdtName(pdt_name)
                .build();
    }
}
