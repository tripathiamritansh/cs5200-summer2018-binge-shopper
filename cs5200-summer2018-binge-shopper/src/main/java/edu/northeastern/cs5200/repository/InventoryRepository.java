package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.InventoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, Integer> {

    InventoryEntity findById(int userId);

    List<InventoryEntity> findBySellerId(int userId);

    InventoryEntity findBySellerIdAndProductId(int userId, int productId);

    @Query("select i from InventoryEntity i where product_id = ?1")
    List<InventoryEntity> findByProductId(int productId);
}
