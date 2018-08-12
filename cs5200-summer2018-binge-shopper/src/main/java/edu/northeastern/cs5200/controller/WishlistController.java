package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.WishlistEntity;
import edu.northeastern.cs5200.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("user/{userId}/add")
    public void addProductinWishlist(@PathVariable int userId, @RequestBody ProductEntity product){
        wishlistService.addProductInWishlist(userId, product);
    }

    @GetMapping("user/{userId}/getProducts")
    public List<WishlistEntity> getProductsInBuyerWishlist(@PathVariable int userId) {
        return wishlistService.getProductsInBuyerWishlist(userId);
    }

    @DeleteMapping("user/{userId}/deleteProducts")
    public void deleteProductFromBuyerWishlist(@PathVariable int userId, @RequestBody ProductEntity product){
        wishlistService.deleteProductFromBuyerWishlist(userId, product);
    }
}
