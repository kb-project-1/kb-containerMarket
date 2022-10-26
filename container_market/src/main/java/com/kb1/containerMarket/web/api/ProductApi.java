package com.kb1.containerMarket.web.api;

import com.kb1.containerMarket.service.ProductService;
import com.kb1.containerMarket.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @GetMapping("/products/{category}")
    public ResponseEntity<?> getProducts(@PathVariable String category, int page) throws Exception {
        return ResponseEntity.ok(new CMRespDto<>("successfully",productService.getProducts(category,page)));
    }
}
