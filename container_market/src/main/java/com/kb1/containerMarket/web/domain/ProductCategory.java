package com.kb1.containerMarket.web.domain;

import com.kb1.containerMarket.web.dto.admin.CategoryRespDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCategory {
    private int category_id;
    private int group_id;
    private String category_name;

    public CategoryRespDto toDto() {
        return CategoryRespDto.builder()
                .id(category_id)
                .name(category_name)
                .build();
    }
}
