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

    @PostMapping("order/{orderId}/seller/{sellerId}/product/{productId}/qty/{qty}/add")
    public TransactionEntity addTransaction(@PathVariable int orderId, @PathVariable int sellerId,
                                            @PathVariable int productId, @PathVariable int qty) throws NotFoundException {
        return transactionService.addTransaction(orderId, sellerId, productId, qty);
    }

    @GetMapping("user/{userId}/getBySeller")
    public List<TransactionEntity> getTransactionBySeller(@PathVariable int userId){
        return transactionService.getTransactionBySeller(userId);
    }

    @GetMapping("order/{orderId}/getByOrder")
    public List<TransactionEntity> getTransactionByOrder(@PathVariable int orderId){
        return transactionService.getTransactionByOrder(orderId);
    }

    @GetMapping("product/{productId}/getByProduct")
    public List<TransactionEntity> getTransactionByProduct(@PathVariable int productId){
        return transactionService.getTransactionByProduct(productId);
    }

    @PostMapping("order/{orderId}/product/{productId}/qty/{qty}/update")
    public TransactionEntity updateTransaction(@PathVariable int orderId, @PathVariable int productId,
                                               @PathVariable int qty){
        return transactionService.updateTransaction(orderId, productId, qty);
    }

    @PostMapping("delete")
    public void deleteTransaction(@PathVariable int transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
