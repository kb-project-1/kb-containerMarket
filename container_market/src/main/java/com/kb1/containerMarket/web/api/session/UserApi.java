package com.kb1.containerMarket.web.api.session;

import com.kb1.containerMarket.service.session.UserInfoService;
import com.kb1.containerMarket.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class UserApi {

    private final UserInfoService userinfoService;

    @GetMapping("/getLogin")
    public ResponseEntity<?> getLogin(Authentication authentication) throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully", userinfoService.getUserInfo(authentication)));
    }
}