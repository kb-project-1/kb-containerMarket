package com.kb1.containerMarket.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/myShop")
@Controller
public class MyShopController {

    @GetMapping("/index")
    public String myShopIndex() {
        return "/myShop/index";
    }

    @GetMapping("/order/list")
    public String myShopOrder() {
        return "/myShop/order/list";
    }
}
