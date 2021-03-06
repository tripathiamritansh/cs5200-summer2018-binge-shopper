package neu.edu.bingeshopper.Repository.Model;

public class OrderTransactionWrapper {

    int productId;
    int sellerId;
    int qty;

    public OrderTransactionWrapper(int productId, int sellerId, int qty) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.qty = qty;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
