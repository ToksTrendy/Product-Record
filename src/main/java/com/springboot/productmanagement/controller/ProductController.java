package com.springboot.productmanagement.controller;


import com.springboot.productmanagement.model.Product;
import com.springboot.productmanagement.utils.ProductData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ProductController {

@GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
    return ResponseEntity.ok().body(ProductData.populate());
}
@GetMapping("/{name}")
    public ResponseEntity<Product>getProduct(@PathVariable String name){
    if (name == null){
        return ResponseEntity.badRequest().build();
    }
    for (Product product : ProductData.populate()){
        if(product.getName() == name) {
            return ResponseEntity.ok().body(product);
        }
    }
    return ResponseEntity.notFound().build();
}
}
