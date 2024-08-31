package org.example.frameworkstudy.member.service;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.member.domain.Member;
import org.example.frameworkstudy.member.domain.MemberStatus;
import org.example.frameworkstudy.member.dto.MemberRequestDto;
import org.example.frameworkstudy.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.example.frameworkstudy.common.util.StringUtils.isNullOrEmpty;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(MemberRequestDto requestMemberDto) {
        validateSignUp(requestMemberDto);

        registerMemberFromDto(requestMemberDto);
    }

    private void validateSignUp(MemberRequestDto requestMemberDto) {
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

    private void registerMemberFromDto(MemberRequestDto requestMemberDto) {
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

}
