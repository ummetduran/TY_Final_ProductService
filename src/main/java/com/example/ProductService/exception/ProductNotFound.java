package com.example.ProductService.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(Long id){
        super(String.format("Product by id:"+id+ "not found"));
    }
}
