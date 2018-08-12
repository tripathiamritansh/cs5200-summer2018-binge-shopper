package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.entity.WishlistEntity;
import edu.northeastern.cs5200.exception.NotFoundException;
import edu.northeastern.cs5200.repository.ProductRepository;
import edu.northeastern.cs5200.repository.UserRepository;
import edu.northeastern.cs5200.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    public void addProductInWishlist(int userId, ProductEntity product) throws NotFoundException{
        UserEntity user = userRepository.findById(userId);
        if(user == null)
            throw new NotFoundException("User not found!");
        ProductEntity pr = productRepository.findById(product.getId());
        if(pr == null) {
            productRepository.save(product);
            pr = product;
        }

        WishlistEntity wishlist = wishlistRepository.findByUserIdAndProductId(userId, product.getId());
        if(wishlist == null)
            wishlistRepository.save(new WishlistEntity(user, pr));
    }

    public List<WishlistEntity> getProductsInBuyerWishlist(int userId){
        return wishlistRepository.findByUserId(userId);
    }

    public void deleteProductFromBuyerWishlist(int userId, ProductEntity product){
        UserEntity user = userRepository.findById(userId);
        if(user == null)
            throw new NotFoundException("User not found!");
        WishlistEntity wishlist = wishlistRepository.findByUserIdAndProductId(userId, product.getId());
        if(wishlist != null)
            wishlistRepository.delete(wishlist);
    }
}
