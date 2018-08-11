//package edu.northeastern.cs5200.controller;
//
//import edu.northeastern.cs5200.entity.ProductEntity;
//import edu.northeastern.cs5200.entity.WishlistEntity;
//import edu.northeastern.cs5200.service.ProductService;
//import edu.northeastern.cs5200.service.WishlistService;
//import javassist.NotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/wishlist")
//public class WishlistController {
//
//    @Autowired
//    private WishlistService wishlistService;
//
//    @PostMapping("user/{userId}/add")
//    public WishlistEntity addProductsinWishlist(@PathVariable int userId, @RequestBody <List> product){
//        return wishlistService.addProducts(product);
//    }
//
//    @GetMapping("{productId}/get")
//    public ProductEntity getProductById(@PathVariable int productId) {
//        return productService.getProductById(productId);
//    }
//
//    @GetMapping("{productName}/getByName")
//    public ProductEntity getProductByName(@PathVariable String productName){
//        return productService.getProductByName(productName);
//    }
//
//    @PutMapping("update")
//    public ProductEntity updateProduct(@RequestBody ProductEntity product) throws NotFoundException {
//        return productService.updateProduct(product);
//    }
//
//    @DeleteMapping("delete")
//    public void deleteProduct(@PathVariable int productId){
//        productService.deleteProduct(productId);
//    }
//}
