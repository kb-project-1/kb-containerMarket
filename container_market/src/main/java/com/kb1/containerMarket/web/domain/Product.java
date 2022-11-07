package com.kb1.containerMarket.web.domain;

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
<<<<<<< HEAD
    private String save_name;
=======

>>>>>>> 38c3427d61cd7b49db874a73094fdbaf9519f234
}
