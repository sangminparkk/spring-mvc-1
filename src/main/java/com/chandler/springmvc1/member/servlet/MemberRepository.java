package com.chandler.springmvc1.member.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static
    private static Long sequence = 0L; // static

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    public void clearAll() {
        store.clear();
    }
}