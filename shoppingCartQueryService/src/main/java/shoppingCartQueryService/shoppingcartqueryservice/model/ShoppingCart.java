package shoppingCartQueryService.shoppingcartqueryservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class ShoppingCart {
    @Id
    private String shoppingCartNumber;
    private List<CartLine> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public ShoppingCart(String shoppingCartNumber, List<CartLine> products) {
        this.shoppingCartNumber = shoppingCartNumber;
        this.products = products;
    }

    public String getShoppingCartNumber() {
        return shoppingCartNumber;
    }

    public void setShoppingCartNumber(String shoppingCartNumber) {
        this.shoppingCartNumber = shoppingCartNumber;
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
