package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.OrderEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity addOrder(UserEntity buyer){
        return orderRepository.save(new OrderEntity(buyer));
    }

    public List<OrderEntity> getOrderByBuyer(int buyerId){
        return orderRepository.findByBuyerId(buyerId);
    }

    public OrderEntity getOrderById(int orderId){
        return orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("No order entity with this id"));
    }

    public OrderEntity updateOrder(OrderEntity order){
        return orderRepository.save(order);
    }

    public void deleteOrder(int orderId){
        orderRepository.deleteById(orderId);
    }
}
