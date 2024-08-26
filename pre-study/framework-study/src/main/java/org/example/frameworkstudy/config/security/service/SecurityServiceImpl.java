package org.example.frameworkstudy.config.security.service;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.member.domain.Member;
import org.example.frameworkstudy.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByName(username);
        Member member = optionalMember.orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 ID 입니다."));

        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getMemberStatus().toString())
                .build();
    }
}
