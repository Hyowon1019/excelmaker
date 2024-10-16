package com.hws.excelmaker.service;

import com.hws.excelmaker.domain.Member;
import com.hws.excelmaker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) //해당 어노테이션이 붙으면 스프링은 타깃을 포인트 컷의 대상으로 자동 등록하며 트랜잭션 관리의 대상이 된다.
//이 선언적 트랜잭션 안에서 예외가 발생했을때,
//체크 예외인 경우에는 컴파일 시부터 에러가 나기 때문에 자동적인 롤백이 실행되지 않지만,
//언체크 예외(a.k.a 런타임 예외)인 경우에는 자동적으로 롤백이 실행되므로
//체크 예외인 경우 롤백시키기 위해서는 @Transactional 의 rollbackFor 속성으로 해당 체크 예외를 적어주어야 한다.
@RequiredArgsConstructor
public class MemberService {

//    @Autowired // @RequiredArgsConstructor로 인해 생략가능.
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        if(memberRepository.findByLoginId(member.getLoginId()).isEmpty()) {
            memberRepository.save(member);
            return member.getId();
        } else {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(String memberEmail) {
        return memberRepository.findByLoginId(memberEmail).get();
    }



}
