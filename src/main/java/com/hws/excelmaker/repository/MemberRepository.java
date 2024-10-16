package com.hws.excelmaker.repository;

import com.hws.excelmaker.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> tempDB = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: 저장된 회원은 {} 입니다.", member);
        tempDB.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return tempDB.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }

    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>(tempDB.values());
        return members;
    }

    public void clearDB() {
        tempDB.clear();
    }
}
