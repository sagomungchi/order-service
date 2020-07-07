package com.pcbang.order.mvp.cart;

import com.pcbang.order.mvp.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
