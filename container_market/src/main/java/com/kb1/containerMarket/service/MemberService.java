package com.kb1.containerMarket.service;

import com.kb1.containerMarket.repository.domain.Member;
import com.kb1.containerMarket.web.dto.JoinReqDto;

public interface MemberService{
    void duplicate(String username) throws Exception;
    void register(JoinReqDto joinReqDto) throws Exception;
    public Member getMember(String username)throws Exception;
    public void updateUser(JoinReqDto joinReqDto) throws Exception;
}
