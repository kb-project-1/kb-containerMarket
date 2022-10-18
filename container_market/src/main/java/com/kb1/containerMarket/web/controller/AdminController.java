package com.kb1.containerMarket.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/productRegistration")
    public String productRegistration() {
        return "admin/productRegistration";
    }

}
