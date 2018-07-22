package edu.northeastern.cs5200.entity;

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
    @Column(name = "productId")
    private int productId;
    @Column(name = "reviewerId")
    private int reviewerId;
}
