package org.godvoyage.godvoyage.service;

import org.godvoyage.godvoyage.constant.MemberRole;
import org.godvoyage.godvoyage.dto.MemberDTO;
import org.godvoyage.godvoyage.entity.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService {
    //회원가입
    Member saveMember(MemberDTO dto);
    String validateMember(MemberDTO dto);

    default Member dtoToEntity(MemberDTO dto){
        Member member = Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .telNum(dto.getTelNum())
                .zipcode(dto.getZipcode())
                .straddress(dto.getStraddress())
                .detaddress(dto.getDetaddress())
                .role(MemberRole.ADMIN)
                .build();
        return member;
    }

    //회원정보 수정?
    Member updateMember(MemberDTO dto);

    //회원 탈퇴하기
    public void deleteMember(Long id);
}
