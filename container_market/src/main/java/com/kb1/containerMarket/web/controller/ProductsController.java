package com.kb1.containerMarket.web.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductsController {

    @GetMapping("/{category}")
    public String loadCollections(@PathVariable @Nullable String category) {
        return "/index";
    }
}
