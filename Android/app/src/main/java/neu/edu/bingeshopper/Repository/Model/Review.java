package neu.edu.bingeshopper.Repository.Model;

import android.support.annotation.Nullable;

import java.util.List;

public class Review {
    private SellerReview sellerReviews;
    private ProductReview productReviews;

    public SellerReview getSellerReviews() {
        return sellerReviews;
    }

    public void setSellerReviews(SellerReview sellerReviews) {
        this.sellerReviews = sellerReviews;
    }

    public ProductReview getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(ProductReview productReviews) {
        this.productReviews = productReviews;
    }
}
