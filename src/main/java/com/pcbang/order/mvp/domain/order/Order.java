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
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "order_item_table",
            joinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderLine> orderItems;

    @Enumerated
    @JoinColumn(name = "order_state")
    private OrderState orderState;

    private LocalDateTime orderDate;

    public Order(List<OrderLine> orderItems, LocalDateTime orderDate) {
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.orderState = OrderState.OrderWait;

        if(orderItems == null){
            this.orderItems = new ArrayList<OrderLine>();
        }
    }

    public void addOrderLine(Item item, int quantity) {
        OrderLine orderLine = new OrderLine(item, quantity);
        addOrderLine(orderLine);
    }

    public void addOrderLine(OrderLine orderLine){
        this.orderItems.add(orderLine);
    }

    public void cancelOrder(){
        this.orderState = OrderState.Cancel;
    }

    public Long updateTo(OrderInfo orderInfo) {
        this.orderState = orderInfo.getOrderState();
        this.orderDate = orderInfo.getOrderDate();
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderLines=" + orderItems +
                ", orderState=" + orderState +
                ", orderDate=" + orderDate +
                '}';
    }
}
