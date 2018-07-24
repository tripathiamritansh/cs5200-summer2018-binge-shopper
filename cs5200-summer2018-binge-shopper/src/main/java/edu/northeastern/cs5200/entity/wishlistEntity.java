package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

public class wishlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_wishlist_association"))
    private int userId;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "product_wishlist_association"))
    private int productId;
}
