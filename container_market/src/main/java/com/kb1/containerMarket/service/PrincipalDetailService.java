package com.kb1.containerMarket.service;

import com.kb1.containerMarket.exception.CustomInternalServerErrorException;
import com.kb1.containerMarket.repository.MemberRepository;
import com.kb1.containerMarket.security.PrincipalDetails;
import com.kb1.containerMarket.web.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = null;

        try {
            member = memberRepository.findMemberByUsername(username);
        } catch (Exception e) {
            throw new CustomInternalServerErrorException("회원 정보 조회 오류");
        }

        if(member == null) {
            throw new UsernameNotFoundException("잘못된 사용자 정보");
        }

        return new PrincipalDetails(member);

    }
}
