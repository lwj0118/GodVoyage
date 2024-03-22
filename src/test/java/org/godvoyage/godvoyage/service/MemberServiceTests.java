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
        MemberDTO dto = MemberDTO.builder()
                .email("admin@godvoyage.com")
                .password("1234")
                .name("관리자계정")
                .address("서울시 강남구")
                .telNum("01012345678")
                .build();
        Member member = memberService.saveMember(dto);

    }
}
