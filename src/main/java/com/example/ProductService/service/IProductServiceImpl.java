package com.example.ProductService.service;

import com.example.ProductService.amqp.Message;
import com.example.ProductService.amqp.config.Producer;
import com.example.ProductService.models.dto.ProductDTO;
import com.example.ProductService.models.entity.Product;
import com.example.ProductService.repository.ProductRepository;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Service
public class IProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;

    @Autowired
    private Producer producer;
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

    @Transactional
    @Override
    public Product update(ProductDTO productDTO) {
        Product product = productRepository.getById(productDTO.getId());
        productDTO.setOldPrice(product.getPrice());
        product.setPrice(productDTO.getNewPrice());

        Message message = Message.builder().id(java.util.UUID.randomUUID().toString()).message(productDTO).build();
        producer.sendToQueue(message);
        System.out.println(message.toString());
        return productRepository.save(product);
    }
}
