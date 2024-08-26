package org.example.frameworkstudy.member.domain;

import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.member.dto.ResponseMemberDto;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class MemberDetail extends User {

    private final ResponseMemberDto responseMemberDto;

    private String accessToken;
    private String refreshToken;

    public MemberDetail(ResponseMemberDto responseMemberDto) {
        super(responseMemberDto.getName(),
                "",
                AuthorityUtils.createAuthorityList(
                        responseMemberDto.getMemberStatus().toString()));
        this.responseMemberDto = responseMemberDto;
    }

}
