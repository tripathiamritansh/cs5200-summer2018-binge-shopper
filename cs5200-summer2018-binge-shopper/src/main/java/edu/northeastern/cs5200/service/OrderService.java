package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.inventoryEntity;
import edu.northeastern.cs5200.entity.orderEntity;
import edu.northeastern.cs5200.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public orderEntity addOrder(int buyerId){
        return orderRepository.save(new orderEntity(buyerId));
    }

    public List<orderEntity> getOrderByBuyer(int buyerId){
        return orderRepository.findByBuyerId(buyerId);
    }

    public Optional<orderEntity> getOrderById(int orderId){
        return orderRepository.findById(orderId);
    }

    public orderEntity updateOrder(orderEntity order){
        return orderRepository.save(order);
    }

    public void deleteOrder(int orderId){
        orderRepository.deleteById(orderId);
    }
}
