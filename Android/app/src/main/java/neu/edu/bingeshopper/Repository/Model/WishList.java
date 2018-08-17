package neu.edu.bingeshopper.Repository.Model;

public class WishList {

    private int id;

    private User user;

    private Product product;

    public WishList(int id, User user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
