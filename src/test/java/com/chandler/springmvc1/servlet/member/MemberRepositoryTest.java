package com.chandler.springmvc1.servlet.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemberRepositoryTest {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @BeforeEach
    void clear() {
        memberRepository.clearAll();
    }

    @Test
    @DisplayName("회원 저장")
    void saveMember() {

        Member member = new Member("memberA", 20);
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());

        assertNotNull(findMember);
        assertEquals(findMember.getId(), 1L);
        assertEquals(findMember.getUsername(), "memberA");
        assertEquals(findMember.getAge(), 20);
    }

    @Test
    @DisplayName("회원 전체 조회")
    void findAllMembers() {
        Member memberA = new Member("memberA", 20);
        Member memberB = new Member("memberB", 30);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        List<Member> members = memberRepository.findAll();
        assertEquals(members.size(), 2L);
    }
}