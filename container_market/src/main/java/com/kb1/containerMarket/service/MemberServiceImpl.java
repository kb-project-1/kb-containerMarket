package com.kb1.containerMarket.service;

import com.kb1.containerMarket.exception.CustomInternalServerErrorException;
import com.kb1.containerMarket.exception.CustomValidationException;
import com.kb1.containerMarket.repository.MemberRepository;
import com.kb1.containerMarket.web.domain.Member;
import com.kb1.containerMarket.web.dto.JoinReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void duplicate(String username) throws Exception{
        Member member = memberRepository.findMemberByUsername(username);

        if(member != null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("email", "이미 사용중인 아이디 입니다.");

            throw new CustomValidationException("Duplicate id",errorMap);
        }
    }

    @Override
    public void register(JoinReqDto joinReqDto) throws Exception {
        // 회원가입 진행
        Member user = joinReqDto.toEntity();
        int result = memberRepository.saveMember(user);
        if(result == 0) {
            throw new CustomInternalServerErrorException("회원가입 중 문제가 발생하였습니다.");
        }
    }

}
