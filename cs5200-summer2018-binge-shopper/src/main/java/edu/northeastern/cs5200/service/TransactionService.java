package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.transactionEntity;
import edu.northeastern.cs5200.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public transactionEntity addTransaction(int productId, int sellerId, int orderId){
        return transactionRepository.save(new transactionEntity(productId, sellerId, orderId));
    }

    public Optional<transactionEntity> getTransactionById(int transactionId){
        return transactionRepository.findById(transactionId);
    }

    public List<transactionEntity> getTransactionByProduct(int productId){
        return transactionRepository.findByProductId(productId);
    }

    public List<transactionEntity> getTransactionBySeller(int sellerId){
        return transactionRepository.findBySellerId(sellerId);
    }

    public List<transactionEntity> getTransactionByOrder(int orderId) {
        return transactionRepository.findByOrderId(orderId);
    }

    public transactionEntity getTransactionByProductAndSellerAndOrder(int orderId, int sellerId, int productId) {
        return transactionRepository.findByOrderIdAndSellerIdAndProductId(orderId, sellerId, productId);
    }

    public transactionEntity updateTransaction(transactionEntity transaction){
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int transactionId){
        transactionRepository.deleteById(transactionId);
    }
}
