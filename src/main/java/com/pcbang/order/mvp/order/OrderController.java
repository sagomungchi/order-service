package com.pcbang.order.mvp.order;

import com.pcbang.order.mvp.domain.order.dto.OrderInfo;
import com.pcbang.order.mvp.domain.order.dto.OrderRequests;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity purchase(@ModelAttribute OrderRequests orderRequests){
        Long id = orderService.orderItem(orderRequests.getRequests());
        return ResponseEntity.created(URI.create("/carts/" + id)).build();
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

    @PostMapping("/{id}")
    public ResponseEntity updateOrder(@PathVariable Long id, @ModelAttribute OrderInfo orderInfo){
        orderService.updateOrder(id, orderInfo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
