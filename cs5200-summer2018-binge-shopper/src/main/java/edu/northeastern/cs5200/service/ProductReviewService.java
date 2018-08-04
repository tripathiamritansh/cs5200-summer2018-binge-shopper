package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.ProductReviewEntity;
import edu.northeastern.cs5200.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public ProductReviewEntity addProductReview(ProductReviewEntity productReview){
        return productReviewRepository.save(productReview);
    }

    public Optional<ProductReviewEntity> getProductReviewById(int id){
        return productReviewRepository.findById(id);
    }

    public List<ProductReviewEntity> getProductReviewByProduct(int productId){
        return productReviewRepository.findByProductId(productId);
    }

    public List<ProductReviewEntity> getProductReviewByReviewer(int reviewerId){
        return productReviewRepository.findByReviewerId(reviewerId);
    }

    public ProductReviewEntity getProductReviewByProductAndReviewer(int productId, int reviewerId){
        return productReviewRepository.findByProductIdAndReviewerId(productId, reviewerId);
    }

    public ProductReviewEntity updateProductReview(ProductReviewEntity productReview){
        return productReviewRepository.save(productReview);
    }

    public void deleteProductReview(int productReviewId){
        productReviewRepository.deleteById(productReviewId);
    }
}
