package com.pcbang.order.mvp.domain.order.dto;

import com.pcbang.order.mvp.domain.order.OrderLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequests {
    private List<OrderLineInfo> orderItems = new ArrayList<>();

    public void addRequest(OrderLineInfo orderLineInfo){
        orderItems.add(orderLineInfo);
    }

    public List<OrderLine> toEntity(){
        List<OrderLine> orderLines = new ArrayList<>();
        orderItems.forEach(orderLineInfo -> {
            orderLines.add(orderLineInfo.toEntity());
        });
        return orderLines;
    }

    @Override
    public String toString() {
        return "OrderRequests{" +
                "orderItems=" + orderItems +
                '}';
    }
}
