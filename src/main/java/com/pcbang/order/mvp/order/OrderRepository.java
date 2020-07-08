package com.pcbang.order.mvp.order;

import com.pcbang.order.mvp.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
