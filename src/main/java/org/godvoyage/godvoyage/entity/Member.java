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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_seq")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String address;
    private String telNum;
    @Enumerated(EnumType.STRING)
    private MemberRole role;

}
