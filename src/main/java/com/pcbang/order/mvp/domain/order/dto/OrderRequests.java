package com.pcbang.order.mvp.domain.cart.dto;

import com.pcbang.order.mvp.domain.cart.OrderLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequests {
    private List<OrderLineInfo> requests = new ArrayList<>();

    public void addRequest(OrderLineInfo orderLineInfo){
        requests.add(orderLineInfo);
    }

    public List<OrderLine> toEntity(){
        List<OrderLine> orderLines = new ArrayList<>();
        requests.forEach(orderLineInfo -> {
            orderLines.add(orderLineInfo.toEntity());
        });
        return orderLines;
    }

    @Override
    public String toString() {
        return "OrderRequests{" +
                "requests=" + requests +
                '}';
    }
}
