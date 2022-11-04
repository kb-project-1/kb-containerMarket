package com.kb1.containerMarket.repository;

import com.kb1.containerMarket.web.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    public Member findMemberByUsername(String username) throws Exception;
    public int saveMember(Member member) throws Exception;
}
