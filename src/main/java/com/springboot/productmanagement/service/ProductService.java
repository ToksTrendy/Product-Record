package com.springboot.productmanagement.service;

import com.springboot.productmanagement.entity.Product;
import com.springboot.productmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<Product> getAllProduct(){return productRepository.findAll();}
    public Product getProduct(@PathVariable Integer id) {
        productRepository.findById(id);
        return null;
    }


    public Product createProduct(Product product) {return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Integer id = product.getId();
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
