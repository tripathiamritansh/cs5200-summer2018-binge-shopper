package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.wishlistEntity;
import edu.northeastern.cs5200.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public wishlistEntity addWishlist(int userId, int productId){
        return wishlistRepository.save(new wishlistEntity(userId, productId));
    }

    public Optional<wishlistEntity> getWishlistById(int wishlistId){
        return wishlistRepository.findById(wishlistId);
    }

    public List<wishlistEntity> getWishlistByUser(int userId){
        return wishlistRepository.findByUserId(userId);
    }

    public List<wishlistEntity> getWishlistByProduct(int productId){
        return wishlistRepository.findByProductId(productId);
    }

    public wishlistEntity getWishlistByUserAndProduct(int userId, int productId) {
        return wishlistRepository.findByUserIdAndProductId(userId, productId);
    }

    public wishlistEntity updateWishlist(wishlistEntity wishlist){
        return wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(int wishlistId){
        wishlistRepository.deleteById(wishlistId);
    }
}
