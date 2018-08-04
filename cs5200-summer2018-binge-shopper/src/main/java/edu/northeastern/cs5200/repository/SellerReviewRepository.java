package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entity.SellerReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerReviewRepository extends CrudRepository<SellerReviewEntity, Integer> {

    List<SellerReviewEntity> findBySellerId(int sellerId);

    List<SellerReviewEntity> findByBuyerId(int buyerId);

    SellerReviewEntity findBySellerIdAndBuyerId(int sellerId, int buyerId);

}
