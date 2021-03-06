package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.OrderEntity;
import edu.northeastern.cs5200.service.OrderService;
import edu.northeastern.cs5200.wrapper.OrderTranscationWrapper;
import edu.northeastern.cs5200.wrapper.ProductsWrapper;
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

    @GetMapping("user/{userId}/getOrder")
    public List<OrderEntity> getOrderByBuyer(@PathVariable int userId){
        return orderService.getOrderByBuyer(userId);
    }

    @PutMapping("{orderId}/update")
    public OrderEntity updateOrder(@PathVariable int orderId, @RequestBody List<OrderTranscationWrapper> transactions){
        return orderService.updateOrderForBuyer(orderId, transactions);
    }

    @DeleteMapping("{orderId}/deleteOrder")
    public void deleteOrder(@PathVariable int orderId){
        orderService.deleteOrder(orderId);
    }

    @DeleteMapping("{orderId}/deleteProducts")
    public void deleteProductsFromOrder(@PathVariable int orderId, @RequestBody List<ProductsWrapper> products){
        orderService.deleteProductsFromOrder(orderId, products);
    }
}
