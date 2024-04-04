package org.godvoyage.godvoyage.service;

import org.godvoyage.godvoyage.dto.MemberDTO;
import org.godvoyage.godvoyage.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    MemberService memberService;

    @Test
    public void saveMemberTest(){
        System.out.println(memberService);
        MemberDTO dto = MemberDTO.builder()
                .email("aaa@godvoyage.com")
                .password("1234")
                .name("관리자계정")
                .zipcode("123456")
                .straddress("서울시 강남구")
                .detaddress("젤비싼땅")
                .telNum("01012345678")
                .build();
        Member member = memberService.saveMember(dto);

    }
}
