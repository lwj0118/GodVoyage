package org.godvoyage.godvoyage.repository;

import org.godvoyage.godvoyage.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);
}
