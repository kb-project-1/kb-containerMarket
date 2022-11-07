package com.kb1.containerMarket.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Null;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/product/register")
    public String productRegister() {
        return "admin/product_registration";
    }


    @GetMapping("/product/update/{productId}")
    public String updateProduct(@PathVariable int productId) {
        return "admin/product_update";
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
