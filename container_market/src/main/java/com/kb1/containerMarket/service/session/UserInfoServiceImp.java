package com.kb1.containerMarket.service.session;

import com.kb1.containerMarket.service.MemberService;
import com.kb1.containerMarket.repository.domain.Member;
import com.kb1.containerMarket.web.dto.session.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImp implements UserInfoService{

    private final MemberService memberService;

    @Override
    public UserResponseDto getUserInfo(Authentication authentication) throws Exception {
        UserResponseDto dto = null;
        Member member = null;

        if(authentication != null) {
            member = memberService.getMember(authentication.getName());
            dto = UserResponseDto.builder()
                    .username(authentication.getName())
                    .name(member.getName())
                    .email(member.getEmail())
                    .phone(member.getPhone())
                    .role_id(member.getRole_id())
                    .build();
        }else{

        }
        return dto;
    }
}
