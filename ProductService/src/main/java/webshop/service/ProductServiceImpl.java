package webshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webshop.domain.Product;
import webshop.exception.custom.EntityAlreadyExistException;
import webshop.exception.custom.EntityNotFoundException;
import webshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(String id) {
        log.info("Getting product with product number {} ",id);
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id:"+ id+" not found"));
    }

    @Override
    public List<Product> findAll() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        log.info("Checking if product already exist");
        Optional<Product> mayBeProduct = productRepository.findById(product.getProductNo());
        if(mayBeProduct.isPresent()) throw  new EntityAlreadyExistException("Product already exist with product number "+product.getProductNo());
        log.info("Save of product with information {}",product);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        log.info("Checking if product already exist");
        Optional<Product> mayBeProduct = productRepository.findById(product.getProductNo());
        if(mayBeProduct.isEmpty()) throw  new EntityNotFoundException("Product with id:"+ product.getProductNo()+" not found");
        log.info("update of product with information {}",product);
        return productRepository.save(product);
    }

    @Override
    public void remove(String id) {
        log.info("Removing product with id {}",id);
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id:"+ id+" not found"));
        productRepository.delete(product);
    }

    @Override
    public boolean validate(String productNo, int qty) {
        log.info("Checking quantity {} is in stock for product no {}",qty,productNo);
        Product product = productRepository.findById(productNo).orElseThrow(() -> new EntityNotFoundException("Product with id:"+ productNo+" not found"));
        return product.getNoInStock()<=qty;
    }
}
