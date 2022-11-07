package com.kb1.containerMarket.web.dto.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSizeRespDto {
    int sizeId;
    String sizeName;
}
