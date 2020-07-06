package com.pcbang.order.mvp.item;

import com.pcbang.order.mvp.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
