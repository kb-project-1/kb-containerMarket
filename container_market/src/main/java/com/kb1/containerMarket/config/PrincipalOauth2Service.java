package com.kb1.containerMarket.config;

import com.kb1.containerMarket.repository.MemberRepository;
import com.kb1.containerMarket.security.PrincipalDetails;
import com.kb1.containerMarket.web.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2Service extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User: {}", oAuth2User.getAttributes());
        log.info("userRequest: {}", userRequest.getClientRegistration());
        String provider = userRequest.getClientRegistration().getClientName();
        PrincipalDetails principalDetails = null;

        try {
            principalDetails = getPrincipalDetails(provider, oAuth2User.getAttributes());
        } catch (Exception e) {
            throw new OAuth2AuthenticationException("login failed");
        }

        return principalDetails;
    }

    private PrincipalDetails getPrincipalDetails(String provider, Map<String, Object> attributes) throws Exception {
        Member member = null;

        Map<String,Object> oauth2Attributes = null;
        String username = null;

        if(provider.equalsIgnoreCase("google")) {
            oauth2Attributes = attributes;
        } else if(provider.equalsIgnoreCase("naver")) {
            oauth2Attributes = (Map<String, Object>) attributes.get("response");
        }

        username = (String)oauth2Attributes.get("username");

        member = memberRepository.findMemberByUsername(username);

        if(member == null) {
            // 회원가입
            member = Member.builder()
                    .email(username)
                    .password(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()))
                    .name((String) oauth2Attributes.get("name"))
                    .provider(provider)
                    .role_id(1)
                    .build();

            memberRepository.saveMember(member);
        } else if (member.getProvider() == null) {
            member.setProvider(provider);
        } else if(!member.getProvider().contains(provider)) {
            member.setProvider(member.getProvider() + "," + provider);
            memberRepository.updateProvider(member);
        }

        return new PrincipalDetails(member, oauth2Attributes);
    }
}
