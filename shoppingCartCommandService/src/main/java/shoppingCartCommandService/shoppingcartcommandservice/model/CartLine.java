package shoppingCartCommandService.shoppingcartcommandservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CartLine {
    @Id
    private String cartLineNumber;
    private int productId;
    private int quantity;

    public CartLine() {
    }

    public CartLine(String cartLineNumber, int productId, int quantity) {
        this.cartLineNumber = cartLineNumber;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getCartLineNumber() {
        return cartLineNumber;
    }

    public void setCartLineNumber(String cartLineNumber) {
        this.cartLineNumber = cartLineNumber;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
