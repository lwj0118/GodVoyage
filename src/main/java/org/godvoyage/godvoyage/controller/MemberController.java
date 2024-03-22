package org.godvoyage.godvoyage.controller;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.dto.MemberDTO;
import org.godvoyage.godvoyage.entity.Member;
import org.godvoyage.godvoyage.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/member/loginForm";
    }

}
