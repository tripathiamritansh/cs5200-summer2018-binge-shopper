package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.productReviewEntity;
import edu.northeastern.cs5200.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public productReviewEntity addProductReview(productReviewEntity productReview){
        return productReviewRepository.save(productReview);
    }

    public Optional<productReviewEntity> getProductReviewById(int id){
        return productReviewRepository.findById(id);
    }

    public List<productReviewEntity> getProductReviewByProduct(int productId){
        return productReviewRepository.findByProductId(productId);
    }

    public List<productReviewEntity> getProductReviewByReviewer(int reviewerId){
        return productReviewRepository.findByReviewerId(reviewerId);
    }

    public productReviewEntity getProductReviewByProductAndReviewer(int productId, int reviewerId){
        return productReviewRepository.findByProductIdAndReviewerId(productId, reviewerId);
    }

    public productReviewEntity updateProductReview(productReviewEntity productReview){
        return productReviewRepository.save(productReview);
    }

    public void deleteProductReview(int productReviewId){
        productReviewRepository.deleteById(productReviewId);
    }
}
