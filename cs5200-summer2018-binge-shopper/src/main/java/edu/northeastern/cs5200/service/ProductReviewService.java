package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.ProductEntity;
import edu.northeastern.cs5200.entity.ProductReviewEntity;
import edu.northeastern.cs5200.entity.SellerReviewEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.repository.ProductRepository;
import edu.northeastern.cs5200.repository.ProductReviewRepository;
import edu.northeastern.cs5200.repository.SellerReviewRepository;
import edu.northeastern.cs5200.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public ProductReviewEntity addProductReview(int userId, int productId,
                                                ProductReviewEntity productReview) throws NotFoundException{
        UserEntity user = userRepository.findById(userId);
        if(user == null)
            throw new NotFoundException("User not found!");
        ProductEntity product = productRepository.findById(productId);
        if(product == null)
            throw new NotFoundException("Product not found!");
        productReview.setReviewer(user);
        productReview.setProduct(product);
        return productReviewRepository.save(productReview);
    }

    public ProductReviewEntity getProductReviewById(int id){
        return productReviewRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No product review entity with this id"));
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

    public ProductReviewEntity updateProductReview(ProductReviewEntity productReview) throws NotFoundException{
        ProductEntity product = productRepository.findById(productReview.getProduct().getId());
        if(product == null)
            throw new NotFoundException("Product not found!");
        UserEntity user = userRepository.findById(productReview.getReviewer().getId());
        if(user == null)
            throw new NotFoundException("User not found!");
        ProductReviewEntity pr = productReviewRepository.findById(productReview.getId()).orElseThrow(()-> new EntityNotFoundException("No seller review entity with this id"));
        pr.setReview(productReview.getReview());
        pr.setRating(productReview.getRating());
        return productReviewRepository.save(pr);
    }

    public void deleteProductReview(int productReviewId){
        productReviewRepository.deleteById(productReviewId);
    }
}
