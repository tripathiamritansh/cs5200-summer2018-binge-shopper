package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.productEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<productEntity, Integer> {
    productEntity findById(int productId);
}
