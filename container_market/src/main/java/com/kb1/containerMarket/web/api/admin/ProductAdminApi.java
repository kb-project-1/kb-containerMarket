package com.kb1.containerMarket.web.api.admin;

import com.kb1.containerMarket.aop.annotation.LogAspect;
import com.kb1.containerMarket.aop.annotation.ValidAspect;
import com.kb1.containerMarket.web.dto.CMRespDto;
import com.kb1.containerMarket.web.dto.admin.ProductRegisterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class ProductAdminApi {

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(@Valid @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                                BindingResult bindingResult) {

        return ResponseEntity.created(null)
                .body(new CMRespDto<>(1,"Register Successfully",null));
    }
}