package com.kb1.containerMarket.web.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ProductRespDto {

    private int pdtId;
    private String pdtName;
    private int pdtPrice;
    private String simpleInfo;
    private String detailInfo;
    private String pdtColor;
    private String sizeName;
}
