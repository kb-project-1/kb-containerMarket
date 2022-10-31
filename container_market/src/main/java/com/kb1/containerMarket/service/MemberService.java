package com.kb1.containerMarket.service;

import com.kb1.containerMarket.web.domain.Member;
import com.kb1.containerMarket.web.dto.JoinReqDto;

public interface MemberService{
    void duplicate(String username) throws Exception;
    void register(JoinReqDto joinReqDto) throws Exception;
    public Member getMember(String username)throws Exception;
}
