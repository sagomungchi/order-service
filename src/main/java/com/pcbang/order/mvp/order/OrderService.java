package com.pcbang.order.mvp.order;

import com.pcbang.order.mvp.domain.order.Order;
import com.pcbang.order.mvp.domain.order.dto.OrderInfo;
import com.pcbang.order.mvp.domain.order.dto.OrderLineInfo;
import com.pcbang.order.mvp.domain.item.Item;
import com.pcbang.order.mvp.item.ItemRepository;
import com.pcbang.order.mvp.item.NotFoundItemException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        order.cancelOrder();
    }

    @Transactional
    public Long orderItem(List<OrderLineInfo> orderLineInfos){
        Order order = new Order(null, LocalDateTime.now());

        for (OrderLineInfo orderLineInfo : orderLineInfos){
            Item item = itemRepository.findById(orderLineInfo.getItem().getId()).orElseThrow(NotFoundItemException::new);
            try {
                order.addOrderLine(item, orderLineInfo.getQuantity());
            } catch (Exception e) {

            }
        }
        return orderRepository.save(order).getId();
    }

    @Transactional(readOnly = true)
    public List<OrderInfo> findAll() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderInfo.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderInfo findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(NotFoundOrderException::new);
        return modelMapper.map(order, OrderInfo.class);
    }

    public void updateOrder(Long id, OrderInfo orderInfo) {
        Order order = orderRepository.findById(id).orElseThrow(NotFoundOrderException::new);
        order.updateTo(orderInfo);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
