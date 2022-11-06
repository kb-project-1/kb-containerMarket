package com.kb1.containerMarket.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Builder
@Data
public class ProductRespDto {

    private int pdtId;
    private String pdtName;
    private int pdtPrice;
    private String simpleInfo;
    private String detailInfo;
    private Map<String, List<Map<String, Object>>> pdtColors;
}
