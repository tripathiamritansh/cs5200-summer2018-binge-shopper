package edu.northeastern.cs5200.entity;

import javax.persistence.*;
import java.util.Date;

public class orderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "buyerId")
    private int buyerId;
}
