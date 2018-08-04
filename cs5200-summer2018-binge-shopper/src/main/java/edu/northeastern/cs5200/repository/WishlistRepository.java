package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.WishlistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends CrudRepository<WishlistEntity, Integer> {

    List<WishlistEntity> findByUserId(int userId);

    List<WishlistEntity> findByProductId(int productId);

    WishlistEntity findByUserIdAndProductId(int userId, int productId);
}
