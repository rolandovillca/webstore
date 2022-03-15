package shoppingCartCommandService.shoppingcartcommandservice.model;

public class CartLine {

    private String productNo;
    private int quantity;

    public CartLine() {
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
