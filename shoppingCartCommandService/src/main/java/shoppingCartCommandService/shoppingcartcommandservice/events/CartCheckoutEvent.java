package shoppingCartCommandService.shoppingcartcommandservice.events;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */

public class CartCheckoutEvent {
    private String shoppingCartId;

    public CartCheckoutEvent() {
    }

    public CartCheckoutEvent(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @Override
    public String toString() {
        return "CartCheckoutEvent{" +
                "shoppingCartId='" + shoppingCartId + '\'' +
                '}';
    }
}
