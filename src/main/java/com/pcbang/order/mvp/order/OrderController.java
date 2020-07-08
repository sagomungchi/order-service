package com.pcbang.order.mvp.order;

import com.pcbang.order.mvp.domain.order.dto.OrderInfo;
import com.pcbang.order.mvp.domain.order.dto.OrderRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity purchase(@RequestBody OrderRequests orderRequests){
        log.info(orderRequests.toString());
        Long id = orderService.orderItem(orderRequests);
        return ResponseEntity.created(URI.create("/orders/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderInfo>> showAllOrders(){
        List<OrderInfo> orderInfos = orderService.findAll();
        return ResponseEntity.ok(orderInfos);
    }

    @GetMapping("/{id}")
    public ResponseEntity showOrderInfo(@PathVariable Long id){
        OrderInfo orderInfo = orderService.findById(id);
        return ResponseEntity.ok(orderInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity updateOrder(@PathVariable Long id){
        OrderInfo orderInfo = orderService.findById(id);
        return ResponseEntity.ok(orderInfo);
    }
}
