package com.springboot.productmanagement.utils;

import com.springboot.productmanagement.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.springboot.productmanagement.exception.ErrorResponse.buildErrorResponse;

public class Validation {
    private Validation() {}

    public static ResponseEntity<?> validateCreateProductRequest(Product product) {
//        ErrorResponse error = new ErrorResponse()
//                .setDescription("Id should be null, id = " + product.getId())
//                .setErrorCode(HttpStatus.BAD_REQUEST.value())
//                .setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return ResponseEntity.badRequest().body(
                buildErrorResponse("Id should be null, id = " + product.getId(),
                        HttpStatus.BAD_REQUEST
                )
        );
    }

    public static ResponseEntity<?> validateUpdateProductRequest(Product product) {
        return ResponseEntity.badRequest().body(
                buildErrorResponse("Id cannot be null, id = " + product.getId(),
                        HttpStatus.BAD_REQUEST
                )
        );
    }
}
