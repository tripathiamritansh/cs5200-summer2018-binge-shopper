package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "date", nullable = false, updatable = false)
    @UpdateTimestamp
    private Date date;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "order_buyer_association"))
    private UserEntity buyer;

    public OrderEntity() {
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", date=" + date +
                ", buyer=" + buyer +
                '}';
    }

    public OrderEntity(UserEntity buyer) {
        this.buyer = buyer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }
}
