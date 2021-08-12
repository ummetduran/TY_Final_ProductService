package com.example.ProductService.service;

import com.example.ProductService.models.dto.ProductDTO;
import com.example.ProductService.models.entity.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    public List<Product> getAllProduct();
    public Product getProductById(Long id);
    public void reduceById(Long productId, int quantityInBasket);
    public Product update(ProductDTO productDTO);

    void increaseById(Long productId, int quantityInBasket);
}
