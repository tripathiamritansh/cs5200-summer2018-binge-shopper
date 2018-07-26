package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.wishlistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends CrudRepository<wishlistEntity, Integer> {

    List<wishlistEntity> findByUserId(int userId);

    List<wishlistEntity> findByProductId(int productId);

    wishlistEntity findByUserIdAndProductId(int userId, int productId);
}
