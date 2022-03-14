package webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.domain.Product;
import webshop.domain.ProductStockDTO;
import webshop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        product = productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> get(@PathVariable("id") String id){
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> get(){
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}/status")
    public ResponseEntity<ProductStockDTO> getStatus(@PathVariable("id") String id,@RequestParam("qty") int qty){
        boolean status = productService.validate(id,qty);
        return new ResponseEntity<>(new ProductStockDTO(status), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody Product product){
        product.setProductNo(id);
        product = productService.update(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
