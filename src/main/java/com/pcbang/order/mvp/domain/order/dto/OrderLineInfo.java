package com.pcbang.order.mvp.domain.order.dto;

import com.pcbang.order.mvp.domain.order.OrderLine;
import com.pcbang.order.mvp.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderLineInfo {

    private Item item;
    private int quantity;

    @Builder
    public OrderLineInfo(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public OrderLine toEntity(){
        return new OrderLine(item, quantity);
    }

    @Override
    public String toString() {
        return "OrderLineInfo{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
