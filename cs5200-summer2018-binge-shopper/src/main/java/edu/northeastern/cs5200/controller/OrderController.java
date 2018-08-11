//package edu.northeastern.cs5200.controller;
//
//import edu.northeastern.cs5200.entity.InventoryEntity;
//import edu.northeastern.cs5200.entity.OrderEntity;
//import edu.northeastern.cs5200.entity.ProductEntity;
//import edu.northeastern.cs5200.entity.UserEntity;
//import edu.northeastern.cs5200.service.InventoryService;
//import edu.northeastern.cs5200.service.OrderService;
//import edu.northeastern.cs5200.service.ProductService;
//import edu.northeastern.cs5200.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("api/order")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("add")
//    public OrderEntity addOrderByBuyer(@RequestBody UserEntity user) throws Exception{
//        if(user.getUserType() != "Buyer")
//            throw new Exception("User not a buyer| Can't add order!");
//        return orderService.addOrder(user);
//    }
//
//    @GetMapping("get")
//    public List<OrderEntity> getOrderByBuyer(@PathVariable int userId) throws Exception{
//        UserEntity user = userService.getUserById(userId);
//        if(user.getUserType() != "Buyer")
//            throw new Exception("User not a buyer| Can't retrieve order!");
//        return orderService.getOrderByBuyer(userId);
//    }
//
//    @PostMapping("update")
//    public OrderEntity updateOrder(@RequestBody OrderEntity order){
//        return orderService.updateOrder(order);
//    }
//
//    @PostMapping("delete")
//    public void deleteOrder(@PathVariable int orderId){
//        Optional<OrderEntity> order = orderService.getOrderById(orderId);
//        if(order != null)
//            orderService.deleteOrder(order);
//    }
//}
