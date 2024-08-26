package org.example.frameworkstudy.member.service;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.common.entity.JwtToken;
import org.example.frameworkstudy.config.security.JwtProvider;
import org.example.frameworkstudy.member.domain.MemberDetail;
import org.example.frameworkstudy.member.domain.Member;
import org.example.frameworkstudy.member.domain.MemberStatus;
import org.example.frameworkstudy.member.dto.RequestMemberDto;
import org.example.frameworkstudy.member.dto.ResponseMemberDto;
import org.example.frameworkstudy.member.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.example.frameworkstudy.common.util.StringUtils.isNullOrEmpty;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public void signUp(RequestMemberDto requestMemberDto) {
        validateSignUp(requestMemberDto);

        registerMemberFromDto(requestMemberDto);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDetail signIn(RequestMemberDto requestMemberDto) {
        String name = requestMemberDto.getName();
        String password = requestMemberDto.getPassword();

        validateLogin(name, password);

        Member member = getMember(name);
        checkPasswordMatched(password, member.getPassword());

        return getSecurityMember(member, name, password);
    }

    private void validateSignUp(RequestMemberDto requestMemberDto) {
        String password = requestMemberDto.getPassword();
        String validatedPassword = requestMemberDto.getValidatedPassword();
        String name = requestMemberDto.getName();

        validatePassword(password, validatedPassword);
        validateName(name);
    }

    private void validatePassword(String password, String validatedPassword) {
        checkPasswordValid(password);
        checkPasswordValid(validatedPassword);

        checkPasswordEquality(password, validatedPassword);
    }

    private void checkPasswordValid(String password) {
        checkPasswordHasValue(password);
        checkPasswordValue(password);
    }

    private void checkPasswordHasValue(String password) {
        if (isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Password must have value");
        }
    }

    private void checkPasswordValue(String password) {
        if (password.length() < 8 || password.length() > 15) {
            throw new IllegalArgumentException("Password is not valid length");
        }

        if (password.chars().noneMatch(Character::isUpperCase)) {
            throw new IllegalArgumentException("Password must use uppercase");
        }

        if (password.chars().noneMatch(Character::isLowerCase)) {
            throw new IllegalArgumentException("Password must use lowercase");
        }

        if (password.chars().noneMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Password must use digit");
        }
    }

    private void checkPasswordEquality(String password, String validatedPassword) {
        if (!password.equals(validatedPassword)) {
            throw new IllegalArgumentException("incorrect password");
        }
    }

    private void validateName(String name) {
        checkNameHasValue(name);
        checkNameValue(name);
        checkNameDuplicated(name);
    }

    private void checkNameHasValue(String name) {
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name must have value");
        }
    }

    private void checkNameValue(String name) {
        if (name.length() < 4 || name.length() > 10) {
            throw new IllegalArgumentException("Name is not valid length");
        }

        if (name.chars().anyMatch(Character::isUpperCase)) {
            throw new IllegalArgumentException("Name must use only lowercase");
        }

        if (name.chars().noneMatch(Character::isLowerCase)) {
            throw new IllegalArgumentException("Name must use only lowercase");
        }

        if (name.chars().noneMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Password must use digit");
        }
    }

    private void checkNameDuplicated(String name) {
        memberRepository.findByName(name)
                .ifPresent(member -> {
                    throw new IllegalArgumentException("Name must not be duplicated. [Name] : " + name);
                });
    }

    private void registerMemberFromDto(RequestMemberDto requestMemberDto) {
        Member member = requestMemberDto.toMember();
        encodePassword(member);
        changeMemberStatus(member);

        memberRepository.save(member);
    }

    private void encodePassword(Member member) {
        member.encodePassword(passwordEncoder.encode(member.getPassword()));
    }

    private void changeMemberStatus(Member member) {
        member.changeMemberStatus(MemberStatus.USER);
    }

    private void validateLogin(String name, String password) {
        checkNameHasValue(name);
        checkPasswordHasValue(password);
    }

    private Member getMember(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found"));
    }

    private void checkPasswordMatched(String inputPassword, String storedPassword) {
        if (!passwordEncoder.matches(inputPassword, storedPassword)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }

    private MemberDetail getSecurityMember(Member member, String name, String rawPassword) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name, rawPassword);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        JwtToken jwtToken = jwtProvider.generateToken(authentication);

        ResponseMemberDto responseMemberDto = ResponseMemberDto.ofMember(member);
        MemberDetail memberDetail = new MemberDetail(responseMemberDto);

        memberDetail.setAccessToken(jwtToken.getAccessToken());
        memberDetail.setRefreshToken(jwtToken.getRefreshToken());

        return memberDetail;
    }
}
