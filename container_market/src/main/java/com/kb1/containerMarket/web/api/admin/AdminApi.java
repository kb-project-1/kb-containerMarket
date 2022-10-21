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
        productManagementService.registerMst(productRegisterReqDto);
        return ResponseEntity.created(null)
                .body(new CMRespDto<>(1,"Register Successfully",true));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>(1, "Get successfully", productManagementService.getCategoryList()));
    }
}