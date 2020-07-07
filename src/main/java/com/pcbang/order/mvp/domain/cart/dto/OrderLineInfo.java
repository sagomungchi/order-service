package com.pcbang.order.mvp.domain.cart.dto;

import com.pcbang.order.mvp.domain.cart.OrderLine;
import com.pcbang.order.mvp.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineInfo {

    private Item item;

    private int quantity;

    @Builder
    public OrderLineInfo(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public OrderLine toEntity(Item item, int quantity){
        return new OrderLine(item, quantity);
    }
}
