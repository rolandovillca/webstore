package webshop.domain;


public class Product {
    private String productNo;
    private String name;
    private double price;
    private String description;
    private int noInStock;

    public Product() {
    }

    public Product(String productNo, String name, double price, String description, int noInStock) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    @Override
    public String toString() {
        return "Product{" +
                "productNo='" + productNo + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", noInStock=" + noInStock +
                '}';
    }
}
