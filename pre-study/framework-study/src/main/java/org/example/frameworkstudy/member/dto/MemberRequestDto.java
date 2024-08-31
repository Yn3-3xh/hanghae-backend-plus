package org.example.frameworkstudy.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.member.domain.Member;

@Getter
@Setter
public class MemberRequestDto {

    private Long id;
    private String name;
    private String password;
    private String validatedPassword;

    public Member toMember() {
        return Member.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
