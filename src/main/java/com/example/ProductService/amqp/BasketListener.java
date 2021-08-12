package com.example.ProductService.amqp;

import com.example.ProductService.models.BasketProductsMessageWrapper;
import com.example.ProductService.models.dto.BasketProductsDTO;
import com.example.ProductService.service.IProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BasketListener {
    private final IProductService productService;

    public BasketListener(IProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "${ps.rabbit.queue.name.basket-service}")
    public void reduceProductfromDB(BasketProductsMessageWrapper message){
        System.out.println(message);
        BasketProductsDTO product = message.getProductsDTO();
        if(message.getType().equalsIgnoreCase("ADD")) {
            productService.reduceById(product.getProductId(), product.getQuantityInBasket());
        }else{
            productService.increaseById(product.getProductId(), product.getQuantityInBasket());
        }
        }
}
