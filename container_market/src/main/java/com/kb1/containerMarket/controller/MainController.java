package com.kb1.containerMarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//테스트용
@Controller
public class MainController {

    @GetMapping("/member/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping("/member/join")
    public String join() {
        return "/member/join";
    }

    @GetMapping("/myShop/index")
    public String myShopIndex() {
        return "/myShop/index";
    }
    @GetMapping("/myShop/order/list")
    public String myShopOrder() {
        return "/myShop/order/list";
    }
}
