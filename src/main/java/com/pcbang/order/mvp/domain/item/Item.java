package com.pcbang.order.mvp.domain.item;

import com.pcbang.order.mvp.domain.item.dto.ItemInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String distributor;

    private String imageURL;

    private int price;

    private int inventory;

    public Item(String name, String description, String distributor, String imageURL, int price, int inventory){
        this.name = name;
        this.description = description;
        this.distributor = distributor;
        this.imageURL = imageURL;
        this.price = price;
        this.inventory = inventory;
    }

    public void updateTo(ItemInfo itemInfo) {
        this.name = itemInfo.getName();
        this.description = itemInfo.getDescription();
        this.distributor = itemInfo.getDistributor();
        this.imageURL = itemInfo.getImageURL();
        this.price = itemInfo.getPrice();
        this.inventory = itemInfo.getInventory();
    }
}
