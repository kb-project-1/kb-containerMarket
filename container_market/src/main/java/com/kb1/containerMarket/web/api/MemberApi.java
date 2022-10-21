package com.kb1.containerMarket.web.api;

import com.kb1.containerMarket.service.MemberService;
import com.kb1.containerMarket.web.controller.validation.ValidationSequence;
import com.kb1.containerMarket.web.dto.CMRespDto;
import com.kb1.containerMarket.web.dto.JoinReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Validated(ValidationSequence.class) @RequestBody JoinReqDto joinReqDto, BindingResult bindingResult) throws Exception {

        memberService.duplicate(joinReqDto.getUsername());
        memberService.register(joinReqDto);

        return ResponseEntity.created(URI.create("/member/login")).body(new CMRespDto<>(1,"회원가입 성공", joinReqDto.getUsername()));
    }

}
