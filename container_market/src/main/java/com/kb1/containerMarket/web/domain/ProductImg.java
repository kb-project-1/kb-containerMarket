package com.kb1.containerMarket.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductImg {
    private int id;
    private int pdt_id;
    private String origin_name;
    public  String save_name;
}
