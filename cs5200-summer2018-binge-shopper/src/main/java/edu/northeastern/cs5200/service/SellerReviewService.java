package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.entity.SellerReviewEntity;
import edu.northeastern.cs5200.entity.UserEntity;
import edu.northeastern.cs5200.repository.SellerReviewRepository;
import edu.northeastern.cs5200.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SellerReviewService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerReviewRepository sellerReviewRepository;

    public SellerReviewEntity addSellerReview(int buyerId, int sellerId, SellerReviewEntity sellerReview) throws NotFoundException{
        UserEntity buyer = userRepository.findById(buyerId);
        if(buyer == null)
            throw new NotFoundException("Buyer not found!");
        UserEntity seller = userRepository.findById(sellerId);
        if(seller == null)
            throw new NotFoundException("Seller not found!");
        sellerReview.setBuyer(buyer);
        sellerReview.setSeller(seller);
        return sellerReviewRepository.save(sellerReview);
    }

    public SellerReviewEntity getSellerReviewById(int id){
        return sellerReviewRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No seller review entity with this id"));
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

    public SellerReviewEntity updateSellerReview(SellerReviewEntity sellerReview) throws NotFoundException{
        UserEntity buyer = userRepository.findById(sellerReview.getBuyer().getId());
        if(buyer == null)
            throw new NotFoundException("Buyer not found!");
        UserEntity seller = userRepository.findById(sellerReview.getSeller().getId());
        if(seller == null)
            throw new NotFoundException("Seller not found!");
        SellerReviewEntity sr = sellerReviewRepository.findById(sellerReview.getId()).orElseThrow(()-> new EntityNotFoundException("No seller review entity with this id"));
        sr.setReview(sellerReview.getReview());
        sr.setRating(sellerReview.getRating());
        return sellerReviewRepository.save(sr);
    }

    public void deleteSellerReview(int sellerReviewId){
        sellerReviewRepository.deleteById(sellerReviewId);
    }
}
