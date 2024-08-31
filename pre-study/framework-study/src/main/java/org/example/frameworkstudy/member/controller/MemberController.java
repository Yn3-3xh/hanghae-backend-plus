package org.example.frameworkstudy.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.common.controller.BaseApiControllerV1;
import org.example.frameworkstudy.member.dto.MemberRequestDto;
import org.example.frameworkstudy.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController extends BaseApiControllerV1 {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.signUp(memberRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member registered successfully");
    }
}
