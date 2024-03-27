package org.godvoyage.godvoyage.controller;

import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.dto.MemberDTO;
import org.godvoyage.godvoyage.entity.Member;
import org.godvoyage.godvoyage.repository.MemberRepository;
import org.godvoyage.godvoyage.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //페이지 연결
    @GetMapping("/join")
    public String memberForm(){
        return "member/joinForm";
    }
    @PostMapping("/join")
    public String memberForm(MemberDTO memberDTO){
        Member member= memberService.saveMember(memberDTO);
        System.out.println("회원가입 완료: " + member.toString());
        return "redirect:/";
    }
    //가입시 이메일 중복체크--> $.ajax의 url과 일치하게 입력!
    @PostMapping("/emailcheck")
    public ResponseEntity<String> memberMailCheck(MemberDTO memberDTO){
        String result= memberService.validateMember(memberDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/login")
    public String login(){
        return "/member/loginForm";
    }

    @GetMapping("/logininfo")
    public String memberInfo(Principal principal, Model model){
        String loginId = principal.getName();
        Member member = memberRepository.findByEmail(loginId);
        model.addAttribute("member", member);
        return "member/myinfo";
    }
    @GetMapping("/login/error")
    public String login(Model model){
        model.addAttribute("loginErrorMsg",
                "아이디 또는 패스워드를 확인해주세요.");
        return "/member/loginForm";
    }
}
