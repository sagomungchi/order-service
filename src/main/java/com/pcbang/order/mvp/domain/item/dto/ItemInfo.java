package com.pcbang.order.mvp.domain.item.dto;

import com.pcbang.order.mvp.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemInfo {

    private String name;

    private String description;

    private String distributor;

    private String imageURL;

    private int price;

    private int inventory;

    @Builder
    public ItemInfo(String name, String description, String distributor, String imageURL, int price, int inventory) {
        this.name = name;
        this.description = description;
        this.distributor = distributor;
        this.imageURL = imageURL;
        this.price = price;
        this.inventory = inventory;
    }

    public Item toEntity(){
        return new Item(name, description, distributor, imageURL, price, inventory);
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", distributor='" + distributor + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                '}';
    }
}
