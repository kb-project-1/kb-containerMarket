package com.kb1.containerMarket.web.api;

import com.kb1.containerMarket.security.PrincipalDetails;
import com.kb1.containerMarket.service.ProductService;
import com.kb1.containerMarket.web.dto.AddOrderRespDto;
import com.kb1.containerMarket.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProducts(@PathVariable String category, int page) throws Exception {
        return ResponseEntity.ok(new CMRespDto<>("successfully",productService.getProducts(category,page)));
    }
    @GetMapping("/product/{pdtId}")
    public ResponseEntity<?> getProduct(@PathVariable int pdtId) throws Exception {
        return ResponseEntity.ok(new CMRespDto<>("successfully",productService.getProduct(pdtId)));
    }

    @PostMapping("/product/addcart/{pdtId}")
    public ResponseEntity<?> addProductCart(@PathVariable int pdtId, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
        if(principalDetails == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new CMRespDto<>("successfully",productService.addProductCart(pdtId, principalDetails.getUsername())));
    }

    @GetMapping("/product/cart")
    public ResponseEntity<?> getProductCart(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
        if(principalDetails == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new CMRespDto<>("불러오기 성공",productService.getProductCart(principalDetails.getUsername())));
    }

    @PostMapping("/product/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderRespDto product) throws Exception {
        return ResponseEntity.ok(new CMRespDto<>("주문목록 추가 성공",productService.addOrder(product)));
    }
}
