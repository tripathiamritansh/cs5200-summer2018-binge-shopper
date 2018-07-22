package edu.northeastern.cs5200.entity;

import javax.persistence.*;

public class sellerReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "review")
    private String review;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "sellerId")
    private int sellerId;
    @Column(name = "buyerId")
    private int buyerId;
}
