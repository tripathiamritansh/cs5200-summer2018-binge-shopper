package edu.northeastern.cs5200.entity;

import javax.persistence.*;

public class phoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "primary")
    private Boolean primary;
    @Column(name = "userId")
    private int userId;
}
