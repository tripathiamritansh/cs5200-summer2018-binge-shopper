package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.productReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends CrudRepository<productReviewEntity, Integer> {

    List<productReviewEntity> findByProductId(int productId);

    List<productReviewEntity> findByReviewerId(int reviewerId);

    productReviewEntity findByProductIdAndReviewerId(int productId, int reviewerId);

}
