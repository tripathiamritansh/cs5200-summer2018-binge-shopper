package neu.edu.bingeshopper.presentation.ProductLinearList;

import neu.edu.bingeshopper.Repository.Model.Order;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.User;

public class ProductLinearListModel {
    private Product product;
    private User seller;
    private int qty;
    private int price;
    private Order order;

    public ProductLinearListModel(Product product, User seller, int qty, int price) {
        this.product = product;
        this.seller = seller;
        this.qty = qty;
        this.price = price;
    }


    public ProductLinearListModel() {
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