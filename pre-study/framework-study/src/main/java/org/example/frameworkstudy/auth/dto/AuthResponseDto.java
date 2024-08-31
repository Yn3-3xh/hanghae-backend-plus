package org.example.frameworkstudy.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.auth.entity.AuthMember;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthResponseDto {

    private String name;
    private List<String> authority;

    private String accessToken;
    private String refreshToken;

    public static AuthResponseDto ofAuth(AuthMember authMember) {
        return AuthResponseDto.builder()
                .name(authMember.getUsername())
                .authority(authMember.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .build();
    }
}
