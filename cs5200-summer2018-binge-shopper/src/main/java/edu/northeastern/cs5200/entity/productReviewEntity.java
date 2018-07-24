package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

public class productReviewEntity {
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
    private int productId;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "reviewer_review_association"))
    private int reviewerId;
}
