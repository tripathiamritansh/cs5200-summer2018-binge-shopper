package neu.edu.bingeshopper.Repository.Model;

public class ProductReview implements AdapterItem{

    private int id;
    private String review;
    private Float rating;

    private Product product;

    private User reviewer;

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", product=" + product +
                ", reviewer=" + reviewer +
                '}';
    }

    public ProductReview() {
    }

    public ProductReview(String review, Float rating, Product product, User reviewer) {
        this.review = review;
        this.rating = rating;
        this.product = product;
        this.reviewer = reviewer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }
}