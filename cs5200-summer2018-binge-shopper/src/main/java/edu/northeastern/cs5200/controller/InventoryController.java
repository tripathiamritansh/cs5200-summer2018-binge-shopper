//package edu.northeastern.cs5200.controller;
//
//import edu.northeastern.cs5200.entity.AddressEntity;
//import edu.northeastern.cs5200.entity.InventoryEntity;
//import edu.northeastern.cs5200.entity.ProductEntity;
//import edu.northeastern.cs5200.entity.UserEntity;
//import edu.northeastern.cs5200.service.AddressService;
//import edu.northeastern.cs5200.service.InventoryService;
//import edu.northeastern.cs5200.service.ProductService;
//import edu.northeastern.cs5200.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@RestController
//@RequestMapping("api/inventory")
//public class InventoryController {
//
//    @Autowired
//    private InventoryService inventoryService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ProductService productService;
//
//    @PostMapping("user/{sellerId}/add")
//    public InventoryEntity addInventoryBySeller(@PathVariable int userId, @RequestBody ProductEntity product) throws Exception{
//        UserEntity user = userService.getUserById(userId);
//        if(user.getUserType() != "Seller")
//            throw new Exception("User not a seller| Can't add inventory!");
//        return inventoryService.addInventory(user, product);
//    }
//
//    @GetMapping("getSeller")
//    public List<InventoryEntity> getAllSellerForProduct(String productName){
//        ProductEntity product = productService.getProductByName(productName).get(0);
//        return inventoryService.getAllSellerForProduct(product);
//    }
//
//    @PostMapping("update")
//    public InventoryEntity updateInventory(@RequestBody InventoryEntity inventory, HttpSession session){
//        return inventoryService.updateInventory(inventory);
//    }
//
//    @PostMapping("delete")
//    public void deleteInventory(@PathVariable int inventoryId){
//        InventoryEntity inventory = inventoryService.getInventoryById(inventoryId);
//        inventoryService.deleteInventory(inventory);
//    }
//}
