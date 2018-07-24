package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_phone_association"))
    private int userId;
}
