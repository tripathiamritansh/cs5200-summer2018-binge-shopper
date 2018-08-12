package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.OrderEntity;
import edu.northeastern.cs5200.service.OrderService;
import edu.northeastern.cs5200.wrapper.OrderTranscationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("user/{userId}/add")
    public OrderEntity addOrderForBuyer(@PathVariable int userId, @RequestBody List<OrderTranscationWrapper> transactions){
        return orderService.addOrderForBuyer(userId, transactions);
    }

    @GetMapping("get")
    public List<OrderEntity> getOrderByBuyer(@PathVariable int userId){
        return orderService.getOrderByBuyer(userId);
    }

    @PostMapping("{orderId}/update")
    public OrderEntity updateOrder(@PathVariable int orderId, @RequestBody List<OrderTranscationWrapper> transactions){
        return orderService.updateOrderForBuyer(orderId, transactions);
    }

    @PostMapping("delete")
    public void deleteOrder(@PathVariable int orderId){
        orderService.deleteOrder(orderId);
    }

    @PostMapping("delete")
    public void deleteProductsFromOrder(@PathVariable int orderId, List<Integer> products){
        orderService.deleteProductsFromOrder(orderId, products);
    }
}
