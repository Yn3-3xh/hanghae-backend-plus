package org.example.frameworkstudy.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.auth.dto.AuthRequestDto;
import org.example.frameworkstudy.auth.dto.AuthResponseDto;
import org.example.frameworkstudy.auth.service.AuthService;
import org.example.frameworkstudy.auth.entity.AuthMember;
import org.example.frameworkstudy.common.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/sign-in")
    public ResponseDto<AuthResponseDto> signIn(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseDto.ofSuccess("SignIn Success.", authService.signIn(authRequestDto));
    }

    // 로그아웃
}
