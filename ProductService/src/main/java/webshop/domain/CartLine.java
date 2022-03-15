package webshop.domain;

public class CartLine {
    private String productNo;
    private int quantity;

    public CartLine() {
    }

    public CartLine(String productNo, int quantity) {
        this.productNo = productNo;
        this.quantity = quantity;
    }


    public String getProductId() {
        return productNo;
    }

    public void setProductId(String productId) {
        this.productNo = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
