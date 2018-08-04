package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.OrderEntity;
import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.TransactionEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionEntity addTransaction(ProductEntity product, UserEntity seller, OrderEntity order){
        return transactionRepository.save(new TransactionEntity(product, seller, order));
    }

    public Optional<TransactionEntity> getTransactionById(int transactionId){
        return transactionRepository.findById(transactionId);
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

    public TransactionEntity updateTransaction(TransactionEntity transaction){
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int transactionId){
        transactionRepository.deleteById(transactionId);
    }
}
