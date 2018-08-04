package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.ProductReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends CrudRepository<ProductReviewEntity, Integer> {

    List<ProductReviewEntity> findByProductId(int productId);

    List<ProductReviewEntity> findByReviewerId(int reviewerId);

    ProductReviewEntity findByProductIdAndReviewerId(int productId, int reviewerId);

}
