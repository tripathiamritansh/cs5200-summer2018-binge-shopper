package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date date;

    @Column(name = "price")
    private int price;

    @Column(name = "qty")
    private int qty;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "inventory_seller_association"))
    private UserEntity seller;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "inventory_product_association"))
    private ProductEntity product;

    public InventoryEntity() {
    }

    public InventoryEntity(int price, int qty, UserEntity seller, ProductEntity product) {
        this.price = price;
        this.qty = qty;
        this.seller = seller;
        this.product = product;
    }

    @Override
    public String toString() {
        return "InventoryEntity{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", qty=" + qty +
                ", seller=" + seller +
                ", product=" + product +
                '}';
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
