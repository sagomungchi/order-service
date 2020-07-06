package com.pcbang.order.mvp.domain.item.dto;

import com.pcbang.order.mvp.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ItemInfo {

    private String name;

    private String description;

    private String distributor;

    private Integer price;

    private Integer inventory;

    private LocalDateTime estimatedArrival;


    @Builder
    public ItemInfo(String name, String description, String distributor, Integer price, Integer inventory, LocalDateTime estimatedArrival) {
        this.name = name;
        this.description = description;
        this.distributor = distributor;
        this.price = price;
        this.inventory = inventory;
        this.estimatedArrival = estimatedArrival;
    }

    public Item toEntity(){
        return new Item(name, description, distributor, price, inventory, estimatedArrival);
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", distributor='" + distributor + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", estimatedArrival=" + estimatedArrival +
                '}';
    }
}
