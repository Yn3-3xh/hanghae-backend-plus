package org.example.frameworkstudy.member.repository;

import org.example.frameworkstudy.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByName(String name);
}
