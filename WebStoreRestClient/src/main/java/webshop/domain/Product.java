package webshop.domain;


public class Product {

    private String product_no;

    private String name;

    private double price;

    private String description;

    private int no_in_stock;

    public Product(){

    }

    public Product(String product_no, String name, double price, String description, int no_in_stock) {
        this.product_no = product_no;
        this.name = name;
        this.price = price;
        this.description = description;
        this.no_in_stock = no_in_stock;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
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

    public int getNo_in_stock() {
        return no_in_stock;
    }

    public void setNo_in_stock(int no_in_stock) {
        this.no_in_stock = no_in_stock;
    }
}
