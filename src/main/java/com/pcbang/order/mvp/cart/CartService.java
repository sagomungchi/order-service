package com.pcbang.order.mvp.cart;

import com.pcbang.order.mvp.domain.cart.Cart;
import com.pcbang.order.mvp.domain.cart.dto.OrderLineInfo;
import com.pcbang.order.mvp.domain.item.Item;
import com.pcbang.order.mvp.item.ItemRepository;
import com.pcbang.order.mvp.item.NotFoundItemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    public void cancelOrder(Long orderId) {
        Cart cart = cartRepository.getOne(orderId);
        cart.cancelOrder();
    }

    @Transactional
    public void orderItem(List<OrderLineInfo> orderLineInfos){
        Cart cart = new Cart(null, LocalDateTime.now());

        for (OrderLineInfo orderLineInfo : orderLineInfos){
            Item item = itemRepository.findById(orderLineInfo.getItem().getId()).orElseThrow(NotFoundItemException::new);
            try {
                cart.addOrderLine(item, orderLineInfo.getQuantity());
            } catch (Exception e) {

            }
        }

        cartRepository.save(cart);
    }

    @Transactional
    private void orderSingleLine(OrderLineInfo orderLineInfo){
        Item item = itemRepository.findById(orderLineInfo.getItem().getId()).orElseThrow(NotFoundItemException::new);

        Cart cart = new Cart(null, LocalDateTime.now());

        try {
            cart.addOrderLine(item, orderLineInfo.getQuantity());
        } catch (Exception e) {

        }

        cartRepository.save(cart);
    }

}
