package com.kb1.containerMarket.web.api.admin;

import com.kb1.containerMarket.aop.annotation.LogAspect;
import com.kb1.containerMarket.aop.annotation.ValidAspect;
import com.kb1.containerMarket.service.admin.ProductManagementService;
import com.kb1.containerMarket.web.dto.CMRespDto;
import com.kb1.containerMarket.web.dto.admin.ProductRegisterReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(@Validated @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                                BindingResult bindingResult) throws Exception {

        String name = productRegisterReqDto.getName();
        Random random = new Random();
        for (int i = 0; i < 70; i++) {
            productRegisterReqDto.setCategory(i / 10 + 1);
            productRegisterReqDto.setName(name + (i+1));
            productRegisterReqDto.setPrice((random.nextInt(10)+1) * 100000);
            productManagementService.registerMst(productRegisterReqDto);
        }
        productManagementService.registerMst(productRegisterReqDto);
        return ResponseEntity.created(null)
                .body(new CMRespDto<>(1,"Register Successfully",true));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>(1, "Get successfully", productManagementService.getCategoryList()));
    }

    @LogAspect
    @GetMapping("/products")
    public ResponseEntity<?> getProducts(int page) throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>(1, "Get successfully", productManagementService.getProducts(page)));
    }
}