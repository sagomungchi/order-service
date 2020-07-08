package com.pcbang.order.mvp.order;

import com.pcbang.order.mvp.domain.order.Order;
import com.pcbang.order.mvp.domain.order.OrderState;
import com.pcbang.order.mvp.domain.order.dto.OrderInfo;
import com.pcbang.order.mvp.domain.order.dto.OrderLineInfo;
import com.pcbang.order.mvp.domain.item.Item;
import com.pcbang.order.mvp.domain.order.dto.OrderRequests;
import com.pcbang.order.mvp.item.ItemRepository;
import com.pcbang.order.mvp.item.NotFoundItemException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        order.cancelOrder();
    }

    @Transactional
    public Long orderItem(OrderRequests orderRequests){
        log.info("Request :" + orderRequests.toString());
        Order order = new Order(orderRequests.toEntity(), LocalDateTime.now());
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

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
