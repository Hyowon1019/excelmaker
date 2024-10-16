package com.hws.excelmaker.controller;

import com.hws.excelmaker.domain.Member;
import com.hws.excelmaker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member) {
        return "members/form/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "members/form/addMemberForm";
        }

        memberRepository.save(member);
        return "redirect:/";
    }
}
