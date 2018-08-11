package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.WishlistEntity;
import edu.northeastern.cs5200.service.WishlistService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("user/{userId}/add")
    public void addProductsinWishlist(@PathVariable int userId, @RequestBody List<Integer> products, List<Integer> quantities){
        wishlistService.addProductsinWishlist(userId, products, quantities);
    }

    @GetMapping("user/{userId}/getProducts")
    public List<WishlistEntity> getProductsByUser(@PathVariable int userId) {
        return wishlistService.getWishlistProductsByUser(userId);
    }

    @PutMapping("user/{userId}/update")
    public void updateProduct(@PathVariable int userId, @RequestBody List<Integer> products, List<Integer> quantities) throws NotFoundException {
        wishlistService.updateProductsInWishlist(userId, products, quantities);
    }

    @DeleteMapping("user/{userId}/deleteProducts")
    public void deleteProduct(@PathVariable int userId, @RequestBody List<ProductEntity> products){
        wishlistService.deleteProductsFromWishlist(userId, products);
    }
}
