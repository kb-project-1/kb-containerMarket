package com.kb1.containerMarket.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String pdt_name;
    private int category_id;
    private int pdt_price;
    private String pdt_simple_info;
    private String pdt_detail_info;
    private List<ProductDetail> pdt_dtls;
    private String save_name;
}
