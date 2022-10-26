package com.kb1.containerMarket.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/product/register")
    public String productRegister() {
        return "admin/product_registration";
    }

    @GetMapping("/products")
    public String products() {
        return "admin/products";
    }

    @GetMapping("/product/register/dtl")
    public String productRegisterDtl() {
        return "admin/product_dtl_registration";
    }
}
