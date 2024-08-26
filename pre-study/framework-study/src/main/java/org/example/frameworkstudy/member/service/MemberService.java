package org.example.frameworkstudy.member.service;

import org.example.frameworkstudy.member.domain.MemberDetail;
import org.example.frameworkstudy.member.dto.RequestMemberDto;

public interface MemberService {

    void signUp(RequestMemberDto requestMemberDto);

    MemberDetail signIn(RequestMemberDto requestMemberDto);
}
