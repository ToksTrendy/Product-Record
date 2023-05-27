package com.springboot.productmanagement.controller;


import com.springboot.productmanagement.entity.Product;
import com.springboot.productmanagement.service.ProductService;
import com.springboot.productmanagement.utils.ProductData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.URISyntax;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static com.springboot.productmanagement.exception.ErrorResponse.buildErrorResponse;
import static com.springboot.productmanagement.utils.Validation.validateCreateProductRequest;
import static com.springboot.productmanagement.utils.Validation.validateUpdateProductRequest;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/product")

public class ProductController {
    private static final Log logger = LogFactory.getLog(ProductController.class);
    private  final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
    return ResponseEntity.ok().body(service.getAllProduct());
}
//@GetMapping("/{name}")
//    public ResponseEntity<Product>getProduct(@PathVariable String name){
//        logger.info("Request to get a product with name : " + name);
//    if (name == null){
//        return ResponseEntity.badRequest().build();
//    }
//    for (Product product : ProductData.populate()){
//        if(product.getName() == name) {
//            return ResponseEntity.ok().body(product);
//        }
//    }
//    return ResponseEntity.notFound().build();
//}
@GetMapping("/{id}")
    public ResponseEntity<?>getProduct(@PathVariable Integer id){
    logger.info("Request to get a product with id : " + id);
    if (id < 1){
        return ResponseEntity.badRequest().body(
                buildErrorResponse("Id cannot be less than 1",BAD_REQUEST)
        );
    }
   Product product = service.getProduct(id);
    if (product != null){
        return ResponseEntity.ok().body(product);
    }
    return ResponseEntity.notFound().build();
}
@PostMapping
public ResponseEntity<?> createProduct(@RequestBody Product product) throws URISyntaxException{
 if (product.getId() != Integer.parseInt(null)){
    return validateCreateProductRequest(product);
}
 Product createdProduct  = service.createProduct(product);
 return ResponseEntity.created(new URI("/api/products/" + createdProduct.getId())).body(createdProduct);
}
@PutMapping
    public ResponseEntity updateProduct(@RequestBody Product product){
        if(product.getId() == Integer.parseInt(null)){
            return validateUpdateProductRequest(product);
        }
    Optional<Product> updatedProduct = Optional.ofNullable(service.updateProduct(product));

    if (updatedProduct.isPresent()) {
        return ResponseEntity.ok(updatedProduct);
    } else {
        return ResponseEntity.badRequest().body(
                buildErrorResponse("Product with Id " + Product.getId() + " does not exist", BAD_REQUEST)
        );
    }
}
@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
}
}
