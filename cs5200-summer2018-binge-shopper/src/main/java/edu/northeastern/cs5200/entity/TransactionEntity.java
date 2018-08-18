package edu.northeastern.cs5200.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    @Column(name = "qty")
    private int qty;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "product_transaction_association"))
    private ProductEntity product;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "seller_transaction_association"))
    private UserEntity seller;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "order_transaction_association"))
    private OrderEntity order;

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", date=" + date +
                ", qty=" + qty +
                ", productId=" + product +
                ", sellerId=" + seller +
                ", orderId=" + order +
                '}';
    }

    public TransactionEntity() {
    }

    public TransactionEntity(ProductEntity product, UserEntity seller, OrderEntity order, int qty) {
        this.product = product;
        this.seller = seller;
        this.order = order;
        this.qty = qty;
        this.date = new Date();
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity productId) {
        this.product = product;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
