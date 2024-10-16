package com.hws.excelmaker.controller;

import com.hws.excelmaker.argumentResolver.Login;
import com.hws.excelmaker.domain.Member;
import com.hws.excelmaker.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/")
    public String loginHome(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");

        //세션에 회원 데이터가 없으면 home
        if(loginMember == null) {
            return "home";
        }

        //세션이 유지되면 loginHome으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String loginHome_V2(@Login Member loginMember, Model model) {

        //세션에 회원 데이터가 없으면 home
        if(loginMember == null) {
            return "home";
        }

        //세션이 유지되면 loginHome으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
