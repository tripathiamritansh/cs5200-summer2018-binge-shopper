package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "seller_review")
public class SellerReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "review")
    private String review;
    @Column(name = "rating")
    private Float rating;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "seller_seller_review_association"))
    private UserEntity seller;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "buyer_seller_review_association"))
    private UserEntity buyer;

    @Override
    public String toString() {
        return "SellerReviewEntity{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", seller=" + seller +
                ", buyer=" + buyer +
                '}';
    }

    public SellerReviewEntity() {
    }

    public SellerReviewEntity(String review, Float rating, UserEntity seller, UserEntity buyer) {
        this.review = review;
        this.rating = rating;
        this.seller = seller;
        this.buyer = buyer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }
}
