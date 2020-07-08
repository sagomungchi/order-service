package com.pcbang.order.mvp.domain.order.dto;

import com.pcbang.order.mvp.domain.item.Item;
import com.pcbang.order.mvp.domain.order.Order;
import com.pcbang.order.mvp.domain.order.OrderLine;
import com.pcbang.order.mvp.domain.order.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfo {

    private List<OrderLine> orderItems;

    private OrderState orderState;

    private LocalDateTime orderDate;

    @Builder
    public OrderInfo(List<OrderLine> orderItems, OrderState orderState, LocalDateTime orderDate) {
        this.orderItems = orderItems;
        this.orderState = orderState;
        this.orderDate = orderDate;
    }

    public Order toEntity(){
        return new Order(orderItems, orderDate);
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderLines=" + orderItems +
                ", orderState=" + orderState +
                ", orderDate=" + orderDate +
                '}';
    }
}
