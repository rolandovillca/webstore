package shoppingCartCommandService.shoppingcartcommandservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CartLine {
    @Id
    private int cartLineNumber;
    private int productId;
    private int quantity;

    public CartLine() {
    }

    public CartLine(int cartLineNumber, int productId, int quantity) {
        this.cartLineNumber = cartLineNumber;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getCartLineNumber() {
        return cartLineNumber;
    }

    public void setCartLineNumber(int cartLineNumber) {
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
