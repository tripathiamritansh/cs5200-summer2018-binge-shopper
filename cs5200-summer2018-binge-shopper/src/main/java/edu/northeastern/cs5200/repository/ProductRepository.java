package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    ProductEntity findById(int productId);
}
