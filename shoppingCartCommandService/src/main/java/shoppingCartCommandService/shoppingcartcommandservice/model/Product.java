package shoppingCartCommandService.shoppingcartcommandservice.model;

public class Product {
    private String productNo;
    private String name;
    private Double price;
    private String description;
    private int noInStock;

    public Product() {
    }

    public Product(String productNo, String name, Double price, String description, int noInStock) {
        this.productNo = productNo;
        this.name = name;
        this.price = price;
        this.description = description;
        this.noInStock = noInStock;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoInStock() {
        return noInStock;
    }

    public void setNoInStock(int noInStock) {
        this.noInStock = noInStock;
    }
}
