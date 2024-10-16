package com.hws.excelmaker.controller;

import com.hws.excelmaker.domain.Member;
import com.hws.excelmaker.form.LoginForm;
import com.hws.excelmaker.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/form/loginForm";
    }

    @PostMapping("/login")
    public String loginForm(@Validated @ModelAttribute(name = "loginForm") LoginForm loginForm, BindingResult bindingResult, @RequestParam(defaultValue = "/", name = "redirectURL") String redirectURL, HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "login/form/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

//        log.info("id : {}, password : {}", loginMember.getLoginId(), loginMember.getPassword());

        if (loginMember == null) { // @ModelAttribute 애노테이션에서 name 을 명시해놓지 않아서 생긴 문제 해결!
            bindingResult.reject("loginFail", "아이디 또는 패스워드가 맞지 않습니다.");
            return "login/form/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 기존 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();

        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginMember", loginMember);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
