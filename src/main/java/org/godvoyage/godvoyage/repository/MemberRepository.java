package org.godvoyage.godvoyage.repository;

import org.godvoyage.godvoyage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member deleteByEmail(String email);

}
