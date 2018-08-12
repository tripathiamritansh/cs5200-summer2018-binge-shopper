package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.OrderEntity;
import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.TransactionEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.exception.NotFoundException;
import edu.northeastern.cs5200.repository.OrderRepository;
import edu.northeastern.cs5200.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionEntity addTransaction(int orderId, int sellerId, int productId, int qty) throws NotFoundException {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("No order entity with this id"));
        if(order == null)
            throw new NotFoundException("Order does not exist!");
        UserEntity seller = userService.getUserById(sellerId);
        if(seller == null)
            throw new NotFoundException("Seller does not exist!");
        ProductEntity product = productService.getProductById(productId);
        if(product == null)
            throw new NotFoundException("Product does not exist!");
        if(qty <= 0)
            throw new NotFoundException("Invalid quantity!");
        TransactionEntity transaction = new TransactionEntity(product, seller, order, qty);
        return transactionRepository.save(transaction);
    }

    public TransactionEntity getTransactionById(int transactionId){
        return transactionRepository.findById(transactionId).orElseThrow(()-> new EntityNotFoundException("No transaction entity with this id"));
    }

    public List<TransactionEntity> getTransactionByProduct(int productId){
        return transactionRepository.findByProductId(productId);
    }

    public List<TransactionEntity> getTransactionBySeller(int sellerId){
        return transactionRepository.findBySellerId(sellerId);
    }

    public List<TransactionEntity> getTransactionByOrder(int orderId) {
        return transactionRepository.findByOrderId(orderId);
    }

    public TransactionEntity getTransactionByProductAndSellerAndOrder(int orderId, int sellerId, int productId) {
        return transactionRepository.findByOrderIdAndSellerIdAndProductId(orderId, sellerId, productId);
    }

    public TransactionEntity updateTransaction(int orderId, int productId, int qty){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("No order entity with this id"));
        if(order == null)
            throw new NotFoundException("Order does not exist!");
        ProductEntity product = productService.getProductById(productId);
        if(product == null)
            throw new NotFoundException("Product does not exist!");
        TransactionEntity transaction = transactionRepository.findByOrderIdAndProductId(orderId, productId);
        if(transaction == null)
            throw new NotFoundException("Transaction for order does not exist!");
        if(qty <= 0)
            throw new NotFoundException("Invalid quantity!");
        transaction.setQty(qty);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int transactionId){
        transactionRepository.deleteById(transactionId);
    }
}
