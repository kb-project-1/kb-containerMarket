package com.kb1.containerMarket.web.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductsController {

    @GetMapping("/category/{category}")
    public String loadCollections(@PathVariable @Nullable String category) {
        return "/index";
    }

    @GetMapping("/product/{pdtId}")
    public String loadProduct(@PathVariable String pdtId) {
        return "/product/product";
    }

    @GetMapping("/order")
    public String loadPayment(){
        return "/order/order";
    }
}
