package org.example.frameworkstudy.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.member.domain.Member;
import org.example.frameworkstudy.member.domain.MemberStatus;

@Getter
@Setter
@Builder
public class MemberResponseDto {

    private Long id;
    private String name;
    private MemberStatus memberStatus;

    public static MemberResponseDto ofMember(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .memberStatus(member.getMemberStatus())
                .build();
    }
}
