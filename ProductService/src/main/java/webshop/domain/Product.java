package webshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@NoArgsConstructor
@ToString
@Document
public class Product {

    @Id
    private String productNo;

    private String name;

    private double price;

    private String description;

    private int noInStock;
}
