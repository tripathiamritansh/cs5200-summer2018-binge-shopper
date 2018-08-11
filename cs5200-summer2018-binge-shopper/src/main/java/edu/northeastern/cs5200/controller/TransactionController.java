//package edu.northeastern.cs5200.controller;
//
//import edu.northeastern.cs5200.entity.OrderEntity;
//import edu.northeastern.cs5200.entity.ProductEntity;
//import edu.northeastern.cs5200.entity.TransactionEntity;
//import edu.northeastern.cs5200.entity.UserEntity;
//import edu.northeastern.cs5200.service.InventoryService;
//import edu.northeastern.cs5200.service.ProductService;
//import edu.northeastern.cs5200.service.TransactionService;
//import edu.northeastern.cs5200.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("api/transaction")
//public class TransactionController {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ProductService productService;
//
//    @PostMapping("user/{userId}/add")
//    public TransactionEntity addTransaction(@PathVariable int userId, @RequestBody ProductEntity product,
//                                            @RequestBody OrderEntity order) throws Exception{
//        UserEntity seller = userService.getUserById(userId);
//        if(seller == null)
//            throw new Exception("Invalid username and password| Try again !");
//        return transactionService.addTransaction(product, seller, order);
//    }
//
//    @GetMapping("user/{userId}/get")
//    public TransactionEntity getTransaction(@PathVariable int userId){
//        return transactionService.getTransactionBySeller(userId).get(0);
//    }
//
//    @PostMapping("update")
//    public TransactionEntity updateTransaction(@RequestBody TransactionEntity transaction){
//        return transactionService.updateTransaction(transaction);
//    }
//
//    @PostMapping("delete")
//    public void deleteTransaction(@PathVariable int transactionId){
//        Optional<TransactionEntity> transaction = transactionService.getTransactionById(transactionId);
//        productService.deleteProduct(transaction);
//    }
//}
