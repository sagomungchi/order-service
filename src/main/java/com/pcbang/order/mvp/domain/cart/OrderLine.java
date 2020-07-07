package com.pcbang.order.mvp.domain.cart;

import com.pcbang.order.mvp.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Embeddable
@NoArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;

    public OrderLine(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
}
