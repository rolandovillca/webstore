package shoppingCartCommandService.shoppingcartcommandservice.model;


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
        CartLine cartLineAlreadyPresent = checkIfPresent(cartLine);
        if(cartLineAlreadyPresent != null) {
            cartLineAlreadyPresent.setQuantity(cartLineAlreadyPresent.getQuantity()+cartLine.getQuantity());
        } else {
            products.add(cartLine);
        }
    }

    public void removeProduct(CartLine cartLine) {
        products.remove(cartLine);
    }

    public void changeQuantity(CartLine newCartLine) {
        this.products.forEach(cartLine -> {
            if (cartLine.getProductNo().equalsIgnoreCase(newCartLine.getProductNo()))
                cartLine.setQuantity(newCartLine.getQuantity());
        });
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartNumber='" + shoppingCartNumber + '\'' +
                ", products=" + products +
                '}';
    }

    private CartLine checkIfPresent(CartLine cartLine) {
        for(CartLine cartLine1: products) {
            if(cartLine.getProductNo().equalsIgnoreCase(cartLine1.getProductNo()))
                return cartLine1;
        }
        return null;
    }
}
