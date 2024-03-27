package org.godvoyage.godvoyage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.godvoyage.godvoyage.dto.MemberDTO;
import org.godvoyage.godvoyage.entity.Member;
import org.godvoyage.godvoyage.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member saveMember(MemberDTO dto) {
        Member member = dtoToEntity(dto);
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        return memberRepository.save(member);
    }


    @Override
    public String validateMember(MemberDTO dto) {
        //이메일로 찾은 멤버가 있으면 "ok"를 리턴 , 없으면 "fail"을 리턴
        //"ok" ---> 이미 회원가입되어있는 email주소 입니다.
        //"fail" ---> 등록가능한 email주소 입니다.
        Member findMember =  memberRepository.findByEmail(dto.getEmail());
        if(findMember != null) {
            return "ok";
        }
        return "fail";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username);
        if(member == null){
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
