package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.SellerReviewEntity;
import edu.northeastern.cs5200.repository.SellerReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerReviewService {

    @Autowired
    private SellerReviewRepository sellerReviewRepository;

    public SellerReviewEntity addSellerReview(SellerReviewEntity sellerReview){
        return sellerReviewRepository.save(sellerReview);
    }

    public Optional<SellerReviewEntity> getSellerReviewById(int id){
        return sellerReviewRepository.findById(id);
    }

    public List<SellerReviewEntity> getSellerReviewBySeller(int sellerId){
        return sellerReviewRepository.findBySellerId(sellerId);
    }

    public List<SellerReviewEntity> getSellerReviewByBuyer(int buyerId){
        return sellerReviewRepository.findByBuyerId(buyerId);
    }

    public SellerReviewEntity getSellerReviewByBuyerAndSeller(int buyerId, int sellerId){
        return sellerReviewRepository.findBySellerIdAndBuyerId(buyerId, sellerId);
    }

    public SellerReviewEntity updateSellerReview(SellerReviewEntity sellerReview){
        return sellerReviewRepository.save(sellerReview);
    }

    public void deleteSellerReview(int sellerReviewId){
        sellerReviewRepository.deleteById(sellerReviewId);
    }
}
