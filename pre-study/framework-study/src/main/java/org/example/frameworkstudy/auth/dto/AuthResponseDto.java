package org.example.frameworkstudy.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthResponseDto {

    private String name;
    private List<String> authority;

    private String accessToken;
    private String refreshToken;

    public static AuthResponseDto ofAuth(UserDetails userDetails, String accessToken, String refreshToken) {
        return AuthResponseDto.builder()
                .name(userDetails.getUsername())
                .authority(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
