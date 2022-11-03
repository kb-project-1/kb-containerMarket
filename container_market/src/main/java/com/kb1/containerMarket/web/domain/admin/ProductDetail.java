package com.kb1.containerMarket.web.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetail {
    private int id;
    private int pdt_id;
    private int size_id;
    private String pdt_color;
    private int pdt_stock;
}
