package org.example.frameworkstudy.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.auth.dto.AuthMemberResponseDto;
import org.example.frameworkstudy.auth.dto.AuthRequestDto;
import org.example.frameworkstudy.auth.dto.AuthResponseDto;
import org.example.frameworkstudy.auth.service.AuthService;
import org.example.frameworkstudy.common.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/sign-in")
    public ResponseEntity<ResponseDto<AuthResponseDto>> signIn(@RequestBody AuthRequestDto authRequestDto) {
        AuthMemberResponseDto authMemberResponseDto = authService.signIn(authRequestDto);
        AuthResponseDto authResponseDto = AuthResponseDto.ofAuth(authMemberResponseDto);

        HttpHeaders headers = createAuthHeader(authMemberResponseDto);
        ResponseDto<AuthResponseDto> responseDto = ResponseDto.ofSuccess("SignIn Success.", authResponseDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(responseDto);
    }

    // 로그아웃

    private HttpHeaders createAuthHeader(AuthMemberResponseDto authMemberResponseDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Token", authMemberResponseDto.getAccessToken());
        headers.add("Refresh-Token", authMemberResponseDto.getRefreshToken());

        return headers;
    }
}
