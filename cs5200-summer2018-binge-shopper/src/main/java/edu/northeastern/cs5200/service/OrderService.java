package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.OrderEntity;
import edu.northeastern.cs5200.entity.TransactionEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.exception.AuthenticationException;
import edu.northeastern.cs5200.exception.NotFoundException;
import edu.northeastern.cs5200.repository.OrderRepository;
import edu.northeastern.cs5200.repository.TransactionRepository;
import edu.northeastern.cs5200.repository.UserRepository;
import edu.northeastern.cs5200.wrapper.OrderTranscationWrapper;
import edu.northeastern.cs5200.wrapper.ProductsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    public OrderEntity addOrderForBuyer(int userId, List<OrderTranscationWrapper> transactions){
        UserEntity user = userRepository.findById(userId);
        if(!user.getUserType().equals("Buyer"))
            throw new AuthenticationException("User not a buyer| Can't retrieve order!");
        OrderEntity order = orderRepository.save(new OrderEntity(user));
        for(OrderTranscationWrapper t: transactions){
            int productId = t.getProductId();
            int sellerId = t.getSellerId();
            int qty = t.getQty();
            transactionService.addTransaction(order.getId(), sellerId, productId, qty);
        }
        return order;
    }

    public List<OrderEntity> getOrderByBuyer(int userId){
        UserEntity user = userRepository.findById(userId);
        if(!user.getUserType().equals("Buyer"))
            throw new AuthenticationException("User not a buyer| Can't retrieve order!");
        return orderRepository.findByBuyerId(userId);
    }

    public OrderEntity getOrderById(int orderId){
        return orderRepository.findById(orderId);
    }

    public OrderEntity updateOrderForBuyer(int orderId, List<OrderTranscationWrapper> transactions){
        OrderEntity order = orderRepository.findById(orderId);
        for(OrderTranscationWrapper t: transactions){
            int productId = t.getProductId();
            int sellerId = t.getSellerId();
            int qty = t.getQty();
            TransactionEntity transaction = transactionRepository.findByOrderIdAndProductId(orderId, productId);
            if(transaction == null)
                throw new NotFoundException("Transaction not found for given order and product");
            if(!userRepository.findById(sellerId).getUserType().equals("Seller"))
                throw new AuthenticationException("User not a seller of product");
            transaction.setSeller(userRepository.findById(sellerId));
            transaction.setQty(qty);
            transactionRepository.save(transaction);
        }
        return order;
    }

    public void deleteProductsFromOrder(int orderId, List<ProductsWrapper> products){
        OrderEntity order = orderRepository.findById(orderId);
        if(order == null)
            throw new NotFoundException("Order with given id does not exist!");
        for(ProductsWrapper product: products){
            int productId = product.getProductId();
            TransactionEntity transaction = transactionRepository.findByOrderIdAndProductId(orderId, productId);
            transactionRepository.delete(transaction);
        }
    }

    public void deleteOrder(int orderId){
        OrderEntity order = orderRepository.findById(orderId);
        if(order != null)
            orderRepository.delete(order);
    }
}