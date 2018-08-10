package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.entity.SellerReviewEntity;
import edu.northeastern.cs5200.service.SellerReviewService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/seller-review")
public class SellerReviewController {

    @Autowired
    private SellerReviewService sellerReviewService;

    @PostMapping("add/buyer/{buyerId}/seller/{sellerId}")
    public SellerReviewEntity addSellerReview(@PathVariable int buyerId, @PathVariable int sellerId,
                                              @RequestBody SellerReviewEntity sellerReview) throws NotFoundException {
        return sellerReviewService.addSellerReview(buyerId, sellerId, sellerReview);
    }

    @GetMapping("user/{userId}/getByBuyer")
    public List<SellerReviewEntity> getSellerReviewByBuyer(@PathVariable int userId){
        return sellerReviewService.getSellerReviewByBuyer(userId);
    }

    @GetMapping("user/{userId}/getBySeller")
    public List<SellerReviewEntity> getSellerReviewBySeller(@PathVariable int userId){
        return sellerReviewService.getSellerReviewBySeller(userId);
    }

    @GetMapping("buyer/{buyerId}/seller/{sellerId}/getByBuyerAndSeller")
    public SellerReviewEntity getSellerReviewByBuyerAndSeller(@PathVariable int buyerId, int sellerId){
        return sellerReviewService.getSellerReviewByBuyerAndSeller(buyerId, sellerId);
    }


    @PostMapping("update")
    public SellerReviewEntity updateProductReview(@RequestBody SellerReviewEntity sellerReview) throws NotFoundException {
        return sellerReviewService.updateSellerReview(sellerReview);
    }

    @PostMapping("delete/{sellerReviewId}")
    public void deleteSellerReview(@PathVariable int sellerReviewId){
        SellerReviewEntity sellerReview = sellerReviewService.getSellerReviewById(sellerReviewId).orElseThrow(()-> new EntityNotFoundException("No seller review entity with this id"));;
        sellerReviewService.deleteSellerReview(sellerReviewId);
    }
}
