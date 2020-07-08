package com.pcbang.order.mvp.domain.order;

import com.pcbang.order.mvp.domain.order.dto.OrderInfo;
import com.pcbang.order.mvp.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "order_line",
            joinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderLine> orderLines;

    @Enumerated
    @JoinColumn(name = "order_state")
    private OrderState orderState;

    private LocalDateTime orderDate;

    public Order(List<OrderLine> orderLines, LocalDateTime orderDate) {
        this.orderLines = orderLines;
        this.orderDate = orderDate;
        this.orderState = OrderState.OrderWait;

        if(orderLines == null){
            this.orderLines = new ArrayList<OrderLine>();
        }
    }

    public void addOrderLine(Item item, int quantity) {
        OrderLine orderLine = new OrderLine(item, quantity);
        addOrderLine(orderLine);
    }

    public void addOrderLine(OrderLine orderLine){
        this.orderLines.add(orderLine);
    }

    public void cancelOrder(){
        this.orderState = OrderState.Cancel;
    }

    public Long updateTo(OrderInfo orderInfo) {
        this.orderLines = orderInfo.getOrderLines();
        this.orderState = orderInfo.getOrderState();
        this.orderDate = orderInfo.getOrderDate();
        return id;
    }
}
