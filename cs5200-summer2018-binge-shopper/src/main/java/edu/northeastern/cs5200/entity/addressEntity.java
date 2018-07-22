package edu.northeastern.cs5200.entity;

import javax.persistence.*;

public class addressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "street1")
    private String street1;
    @Column(name = "street2")
    private String street2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private int zip;
    @Column(name = "primary")
    private Boolean primary;
    @Column(name = "userId")
    private int userId;
}
