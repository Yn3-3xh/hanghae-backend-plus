package org.example.frameworkstudy.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthResponseDto {

    private String username;
    private List<String> authority;

    public static AuthResponseDto ofAuth(AuthMemberResponseDto authMemberResponseDto) {
        return AuthResponseDto.builder()
                .username(authMemberResponseDto.getUsername())
                .authority(authMemberResponseDto.getAuthority())
                .build();
    }
}
