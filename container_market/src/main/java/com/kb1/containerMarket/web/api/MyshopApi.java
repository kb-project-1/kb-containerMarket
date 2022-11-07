package com.kb1.containerMarket.web.api;

import com.kb1.containerMarket.security.PrincipalDetails;
import com.kb1.containerMarket.service.MyshopService;
import com.kb1.containerMarket.web.dto.CMRespDto;
import com.kb1.containerMarket.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myshop")
@RequiredArgsConstructor
public class MyshopApi {
    private final MyshopService myshopService;

    @GetMapping("/order/getList")
    public ResponseEntity<?> getList(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
        return ResponseEntity.ok().body(new CMRespDto("getOrderList", myshopService.getOrderListAll(principalDetails.getUsername())));
    }
}
