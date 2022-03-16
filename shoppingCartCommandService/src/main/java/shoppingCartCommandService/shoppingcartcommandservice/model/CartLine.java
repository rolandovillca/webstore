package shoppingCartCommandService.shoppingcartcommandservice.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "CartLine{" +
                "productNo='" + productNo + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartLine cartLine = (CartLine) o;
        return Objects.equals(productNo, cartLine.productNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo);
    }
}
