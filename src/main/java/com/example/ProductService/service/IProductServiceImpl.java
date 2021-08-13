package com.example.ProductService.service;

import com.example.ProductService.amqp.StockProducer;
import com.example.ProductService.models.ProductPriceChangeMessage;
import com.example.ProductService.amqp.PriceChangeProducer;
import com.example.ProductService.exception.ProductNotFound;
import com.example.ProductService.models.dto.ProductDTO;
import com.example.ProductService.models.entity.Product;
import com.example.ProductService.repository.ProductRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;


    @Autowired
    private PriceChangeProducer priceChangeProducer;
    @Autowired
    private StockProducer stockProducer;
    public IProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> productOpt = productRepository.findById(product.getProductId());
        Product updatedProduct = new Product();
        int quantity = product.getQuantity();
        if(productOpt.isPresent()){
            updatedProduct = productOpt.get();
            System.out.println(updatedProduct);
            quantity+=updatedProduct.getQuantity();
            updatedProduct.setQuantity(quantity);
        }

        return productRepository.save(updatedProduct);
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
    public void increaseById(Long productId, int quantityInBasket) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            System.out.println(product);

            int count = product.getQuantity();
            count+=quantityInBasket;
            product.setQuantity(count);
            productRepository.save(product);
        }else{
            throw new ProductNotFound(productId);
        }
    }

    @Override
    public void reduceById(Long productId, int quantityInBasket) {

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            System.out.println(product);

            int count = product.getQuantity();
            count-=quantityInBasket;
            product.setQuantity(count);
            productRepository.save(product);
            if(count<3){

                stockProducer.sendToQueue(productId, count);
            }
        }else{
            throw new ProductNotFound(productId);
        }

    }

    @Transactional
    @Override
    public Product update(ProductDTO productDTO) {
        Product product = productRepository.getById(productDTO.getProductId());
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setOldPrice(product.getPrice());
        product.setPrice(productDTO.getNewPrice());

        ProductPriceChangeMessage productPriceChangeMessage = ProductPriceChangeMessage.builder().id(java.util.UUID.randomUUID().toString()).message(productDTO).build();
        priceChangeProducer.sendToQueue(productPriceChangeMessage);
        System.out.println(productPriceChangeMessage.toString());
        return productRepository.save(product);
    }


}
