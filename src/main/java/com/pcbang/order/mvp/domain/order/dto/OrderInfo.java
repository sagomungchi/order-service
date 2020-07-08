package com.pcbang.order.mvp.domain.order.dto;

import com.pcbang.order.mvp.domain.order.Order;
import com.pcbang.order.mvp.domain.order.OrderLine;
import com.pcbang.order.mvp.domain.order.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfo {

    private List<OrderLine> orderLines;

    private OrderState orderState;

    private LocalDateTime orderDate;

    @Builder
    public OrderInfo(List<OrderLine> orderLines, OrderState orderState, LocalDateTime orderDate) {
        this.orderLines = orderLines;
        this.orderState = orderState;
        this.orderDate = orderDate;
    }

    public Order toEntity(){
        return new Order(orderLines, orderDate);
    }
}
