package com.kb1.containerMarket.web.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryRespDto {
    private int id;
    private String name;
}
