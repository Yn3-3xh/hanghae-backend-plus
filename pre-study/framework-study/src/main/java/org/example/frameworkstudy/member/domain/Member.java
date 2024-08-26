package org.example.frameworkstudy.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.frameworkstudy.common.entity.AbstractAuditable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AbstractAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Builder
    public Member(Long id,
                  String name,
                  String password,
                  MemberStatus memberStatus) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.memberStatus = memberStatus;
    }

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void changeMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

}
