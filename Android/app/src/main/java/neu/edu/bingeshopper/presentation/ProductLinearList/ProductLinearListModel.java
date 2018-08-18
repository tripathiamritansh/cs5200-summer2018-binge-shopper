package neu.edu.bingeshopper.presentation.ProductLinearList;

import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.Repository.Model.Order;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.Transaction;
import neu.edu.bingeshopper.Repository.Model.User;

public class ProductLinearListModel {
    private Product product;
    private User seller;
    private User buyer;
    private int qty;
    private int price;
    private Order order;
    private Inventory inventory;
    private Transaction transaction;

    public ProductLinearListModel(Product product, User seller, int qty, int price) {
        this.product = product;
        this.seller = seller;
        this.qty = qty;
        this.price = price;
    }


    public ProductLinearListModel() {
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
