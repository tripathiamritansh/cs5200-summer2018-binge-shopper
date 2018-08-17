package neu.edu.bingeshopper.Repository.Model;

import java.util.Objects;

public class CartItem {

    private Product product;
    private User Seller;
    private int qty;
    private Inventory inventory;

    public CartItem(Product product, User seller, int qty, Inventory inventory) {
        this.product = product;
        Seller = seller;
        this.qty = qty;
        this.inventory = inventory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getSeller() {
        return Seller;
    }

    public void setSeller(User seller) {
        Seller = seller;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product) &&
                Objects.equals(Seller, cartItem.Seller);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product, Seller);
    }
}
