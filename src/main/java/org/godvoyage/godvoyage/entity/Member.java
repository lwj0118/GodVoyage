package org.godvoyage.godvoyage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.godvoyage.godvoyage.constant.MemberRole;

import javax.management.relation.Role;

@Entity
@Table(name="member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@SequenceGenerator(name="member_seq", sequenceName = "member_seq", allocationSize = 1)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String zipcode;         //우편번호
    private String straddress;		// 지번 주소
    private String detaddress;		// 상세 주소
    private String telNum;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

}
