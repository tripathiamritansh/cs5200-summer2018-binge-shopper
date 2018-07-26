package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.sellerReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerReviewRepository extends CrudRepository<sellerReviewEntity, Integer> {

    List<sellerReviewEntity> findBySellerId(int sellerId);

    List<sellerReviewEntity> findByBuyerId(int buyerId);

    sellerReviewEntity findBySellerIdAndBuyerId(int sellerId, int buyerId);

}
