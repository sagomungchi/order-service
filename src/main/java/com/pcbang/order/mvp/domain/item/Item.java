package com.pcbang.order.mvp.domain.item;

import com.pcbang.order.mvp.domain.item.dto.ItemInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String distributor;

    private Integer price;

    private Integer inventory;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    public Item(String name, String description, String distributor, Integer price, Integer inventory){
        this.name = name;
        this.description = description;
        this.distributor = distributor;
        this.price = price;
        this.inventory = inventory;
    }

    public Long updateTo(ItemInfo itemInfo) {
        this.name = itemInfo.getName();
        this.description = itemInfo.getDescription();
        this.distributor = itemInfo.getDistributor();
        this.price = itemInfo.getPrice();
        this.inventory = itemInfo.getInventory();
        return id;
    }
}
