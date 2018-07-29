package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.sellerReviewEntity;
import edu.northeastern.cs5200.repository.SellerReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerReviewService {

    @Autowired
    private SellerReviewRepository sellerReviewRepository;

    public sellerReviewEntity addSellerReview(sellerReviewEntity sellerReview){
        return sellerReviewRepository.save(sellerReview);
    }

    public Optional<sellerReviewEntity> getSellerReviewById(int id){
        return sellerReviewRepository.findById(id);
    }

    public List<sellerReviewEntity> getSellerReviewBySeller(int sellerId){
        return sellerReviewRepository.findBySellerId(sellerId);
    }

    public List<sellerReviewEntity> getSellerReviewByBuyer(int buyerId){
        return sellerReviewRepository.findByBuyerId(buyerId);
    }

    public sellerReviewEntity getSellerReviewByBuyerAndSeller(int buyerId, int sellerId){
        return sellerReviewRepository.findBySellerIdAndBuyerId(buyerId, sellerId);
    }

    public sellerReviewEntity updateSellerReview(sellerReviewEntity sellerReview){
        return sellerReviewRepository.save(sellerReview);
    }

    public void deleteSellerReview(int sellerReviewId){
        sellerReviewRepository.deleteById(sellerReviewId);
    }
}
