package com.chandler.springmvc1.spring.project.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("회원 저장, member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findMembers().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findAny();
    }

    public List<Member> findMembers() {
        return new ArrayList<>(store.values());
    }

    public void clearAll() {
        store.clear();
    }

}
