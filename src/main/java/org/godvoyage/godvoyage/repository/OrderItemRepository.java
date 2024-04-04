package org.godvoyage.godvoyage.repository;

import org.godvoyage.godvoyage.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
