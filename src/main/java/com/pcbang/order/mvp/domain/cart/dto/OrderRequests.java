package com.pcbang.order.mvp.domain.cart.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderRequests {
    private List<OrderLineInfo> requests = new ArrayList<>();

    public void addRequest(OrderLineInfo orderLineInfo){
        requests.add(orderLineInfo);
    }
}
