package org.godvoyage.godvoyage.controller;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.dto.MemberDTO;
import org.godvoyage.godvoyage.entity.Member;
import org.godvoyage.godvoyage.repository.MemberRepository;
import org.godvoyage.godvoyage.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String login() {
        return "/member/loginForm";
    }

    @GetMapping("/logininfo")
    public String memberInfo(Principal principal, Model model){
        String loginId = principal.getName();
        Member member = memberRepository.findByEmail(loginId);
        model.addAttribute("member", member);

        return "member/myinfo";
    }
}
