package shoppingCartQueryService.shoppingcartqueryservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class ShoppingCart {

    @Id
    private int ShoppingCartNumber;
    private List<CartLine> products;

    public ShoppingCart() {
    }

    public ShoppingCart(int shoppingCartNumber, List<CartLine> products) {
        ShoppingCartNumber = shoppingCartNumber;
        this.products = products;
    }

    public int getShoppingCartNumber() {
        return ShoppingCartNumber;
    }

    public void setShoppingCartNumber(int shoppingCartNumber) {
        ShoppingCartNumber = shoppingCartNumber;
    }

    public List<CartLine> getProducts() {
        return products;
    }

    public void setProducts(List<CartLine> products) {
        this.products = products;
    }

    public void addProduct(CartLine cartLine) {
        products.add(cartLine);
    }

    public void removeProduct(CartLine cartLine) {
        products.remove(cartLine);
    }

    public void replaceCartLine(CartLine previousCartLine, CartLine newCartLine) {
        int index = products.indexOf(previousCartLine);
        products.set(index, newCartLine);
    }
}
