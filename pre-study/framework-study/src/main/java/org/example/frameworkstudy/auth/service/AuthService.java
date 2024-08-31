package org.example.frameworkstudy.auth.service;

import org.example.frameworkstudy.auth.dto.AuthResponseDto;
import org.example.frameworkstudy.auth.dto.AuthRequestDto;

public interface AuthService {

    AuthResponseDto signIn(AuthRequestDto authRequestDto);
}
