package neu.edu.bingeshopper.Repository.Model;

import java.util.Date;

public class Transaction {

    private int id;

    private Date date;

    private int qty;


    private Product product;

    private User seller;

    private Order order;


    public Transaction(int id, Date date, int qty, Product product, User seller, Order order) {
        this.id = id;
        this.date = date;
        this.qty = qty;
        this.product = product;
        this.seller = seller;
        this.order = order;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
