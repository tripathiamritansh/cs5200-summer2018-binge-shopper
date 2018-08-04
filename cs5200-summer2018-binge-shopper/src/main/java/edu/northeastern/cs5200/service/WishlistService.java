package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.entity.WishlistEntity;
import edu.northeastern.cs5200.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public WishlistEntity addWishlist(UserEntity user, ProductEntity product){
        return wishlistRepository.save(new WishlistEntity(user, product));
    }

    public Optional<WishlistEntity> getWishlistById(int wishlistId){
        return wishlistRepository.findById(wishlistId);
    }

    public List<WishlistEntity> getWishlistByUser(int userId){
        return wishlistRepository.findByUserId(userId);
    }

    public List<WishlistEntity> getWishlistByProduct(int productId){
        return wishlistRepository.findByProductId(productId);
    }

    public WishlistEntity getWishlistByUserAndProduct(int userId, int productId) {
        return wishlistRepository.findByUserIdAndProductId(userId, productId);
    }

    public WishlistEntity updateWishlist(WishlistEntity wishlist){
        return wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(int wishlistId){
        wishlistRepository.deleteById(wishlistId);
    }
}
