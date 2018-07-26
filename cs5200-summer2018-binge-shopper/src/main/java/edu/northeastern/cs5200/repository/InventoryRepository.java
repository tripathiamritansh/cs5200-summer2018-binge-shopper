package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.inventoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<inventoryEntity, Integer> {

    List<inventoryEntity> findBySellerId(int userId);

    inventoryEntity findBySellerIdAndProductId(int userId, int productId);

    @Query("select i from inventoryEntity i where product_id = ?1")
    List<inventoryEntity> findByProductId(int productId);
}
