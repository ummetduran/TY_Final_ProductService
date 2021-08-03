package com.example.ProductService.service;

import com.example.ProductService.models.entity.Product;
import com.example.ProductService.repository.ProductRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;

    public IProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


    @Override
    public Product getProductById(Long id) {

        return productRepository.getById(id);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product update() {
        //TODO
        return null;
    }
}
