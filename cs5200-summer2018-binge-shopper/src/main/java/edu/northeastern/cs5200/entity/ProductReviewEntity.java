package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "product_review")
public class ProductReviewEntity {
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
    @JoinColumn(foreignKey = @ForeignKey(name = "product_review_association"))
    private ProductEntity product;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "reviewer_review_association"))
    private UserEntity reviewer;

    @Override
    public String toString() {
        return "ProductReviewEntity{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", product=" + product +
                ", reviewer=" + reviewer +
                '}';
    }

    public ProductReviewEntity() {
    }

    public ProductReviewEntity(String review, Float rating, ProductEntity product, UserEntity reviewer) {
        this.review = review;
        this.rating = rating;
        this.product = product;
        this.reviewer = reviewer;
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

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserEntity reviewer) {
        this.reviewer = reviewer;
    }
}
