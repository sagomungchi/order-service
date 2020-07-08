package com.pcbang.order.mvp.domain.cart;

import com.pcbang.order.mvp.domain.cart.dto.CartInfo;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "order_line",
            joinColumns = @JoinColumn(name = "cart_id")
    )
    private List<OrderLine> orderLines;

    @Enumerated
    @JoinColumn(name = "order_state")
    private OrderState orderState;

    private LocalDateTime orderDate;

    public Cart(List<OrderLine> orderLines, LocalDateTime orderDate) {
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

    public Long updateTo(CartInfo cartInfo) {
        this.orderLines = cartInfo.getOrderLines();
        this.orderState = cartInfo.getOrderState();
        this.orderDate = cartInfo.getOrderDate();
        return id;
    }
}
