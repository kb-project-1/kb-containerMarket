package com.kb1.containerMarket.repository.domain.admin;

import com.kb1.containerMarket.web.dto.admin.ProductSizeRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSize {
    private int size_id;
    private String size_name;

    public ProductSizeRespDto toDto() {
        return ProductSizeRespDto.builder()
                .sizeId(size_id)
                .sizeName(size_name)
                .build();
    }
}
