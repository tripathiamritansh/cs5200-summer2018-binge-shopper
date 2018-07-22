package edu.northeastern.cs5200.entity;

import javax.persistence.*;

public class productEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private int price;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "qty")
    private int qty;
}
