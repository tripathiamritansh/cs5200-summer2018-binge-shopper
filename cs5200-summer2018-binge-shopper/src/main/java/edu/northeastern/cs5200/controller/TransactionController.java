package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.TransactionEntity;
import edu.northeastern.cs5200.exception.NotFoundException;
import edu.northeastern.cs5200.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("user/{userId}/add")
    public TransactionEntity addTransaction(@PathVariable int orderId, @PathVariable int sellerId,
                                            @PathVariable int productId, @RequestBody TransactionEntity transaction) throws NotFoundException {
        return transactionService.addTransaction(productId, sellerId, orderId, transaction);
    }

    @GetMapping("user/{userId}/getBySeller")
    public List<TransactionEntity> getTransactionBySeller(@PathVariable int userId){
        return transactionService.getTransactionBySeller(userId);
    }

    @GetMapping("order/{orderId}/getByOrder")
    public List<TransactionEntity> getTransactionByOrder(@PathVariable int orderId){
        return transactionService.getTransactionBySeller(orderId);
    }

    @GetMapping("product/{productId}/getByProduct")
    public List<TransactionEntity> getTransactionByProduct(@PathVariable int productId){
        return transactionService.getTransactionByProduct(productId);
    }

    @PostMapping("update")
    public TransactionEntity updateTransaction(@RequestBody TransactionEntity transaction){
        return transactionService.updateTransaction(transaction);
    }

    @PostMapping("delete")
    public void deleteTransaction(@PathVariable int transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
