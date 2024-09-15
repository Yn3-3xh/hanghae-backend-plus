package org.example.frameworkstudy.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.auth.dto.AuthMemberResponseDto;
import org.example.frameworkstudy.auth.dto.AuthRequestDto;
import org.example.frameworkstudy.auth.service.AuthService;
import org.example.frameworkstudy.auth.token.JwtProvider;
import org.example.frameworkstudy.auth.token.JwtToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional(readOnly = true)
    public AuthMemberResponseDto signIn(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationMember(authRequestDto);
        JwtToken jwtToken = jwtProvider.generateToken(authentication);

        return generateAuthResponseDto(authentication, jwtToken);
    }

    private Authentication authenticationMember(AuthRequestDto authRequestDto) {
        String name = authRequestDto.getName();
        String rawPassword = authRequestDto.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name, rawPassword);

        return authenticationManager.authenticate(authenticationToken);
    }

    private AuthMemberResponseDto generateAuthResponseDto(Authentication authentication, JwtToken jwtToken) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtToken.getAccessToken();
        String refreshToken = jwtToken.getRefreshToken();

        return AuthMemberResponseDto.ofAuthMember(userDetails, accessToken, refreshToken);
    }
}
