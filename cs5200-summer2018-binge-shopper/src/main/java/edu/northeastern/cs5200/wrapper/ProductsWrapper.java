package edu.northeastern.cs5200.wrapper;

public class ProductsWrapper {

    int productId;

    public ProductsWrapper() {
    }

    public ProductsWrapper(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
