package edu.northeastern.cs5200.controller;
import edu.northeastern.cs5200.entity.ProductReviewEntity;
import edu.northeastern.cs5200.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product-review")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping("add/user/{userId}/product/{productId}")
    public ProductReviewEntity addProductReview(@PathVariable int userId, @PathVariable int productId,
                                              @RequestBody ProductReviewEntity productReview) throws NotFoundException {
        return productReviewService.addProductReview(userId, productId, productReview);
    }

    @GetMapping("product/{productId}/getByProduct")
    public List<ProductReviewEntity> getProductReviewByProduct(@PathVariable int productId){
        return productReviewService.getProductReviewByProduct(productId);
    }

    @GetMapping("user/{userId}/getByReviewer")
    public List<ProductReviewEntity> getProductReviewByReviewer(@PathVariable int userId){
        return productReviewService.getProductReviewByReviewer(userId);
    }

    @GetMapping("product/{productId}/user/{userId}/getByProductAndReviewer")
    public ProductReviewEntity getProductReviewByProductAndReviewer(@PathVariable int productId, @PathVariable int userId){
        return productReviewService.getProductReviewByProductAndReviewer(productId, userId);
    }


    @PutMapping("update")
    public ProductReviewEntity updateProductReview(@RequestBody ProductReviewEntity productReview) throws NotFoundException {
        return productReviewService.updateProductReview(productReview);
    }

    @PostMapping("delete/{productReviewId}")
    public void deleteSellerReview(@PathVariable int productReviewId){
        productReviewService.deleteProductReview(productReviewId);
    }
}
