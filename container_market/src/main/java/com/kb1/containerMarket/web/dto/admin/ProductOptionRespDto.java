package com.kb1.containerMarket.web.dto.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOptionRespDto {
    private int pdtId;
    private String category;
    private String pdtName;
}
