package com.hws.excelmaker.service;

import com.hws.excelmaker.domain.Member;
import com.hws.excelmaker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> password.equals(m.getPassword()))
                .orElse(null);
    }
}
