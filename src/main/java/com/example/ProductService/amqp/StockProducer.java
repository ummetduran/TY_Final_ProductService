package com.example.ProductService.amqp;

import com.example.ProductService.models.ProductPriceChangeMessage;
import com.example.ProductService.models.dto.StockDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockProducer {
    @Value("${ps.rabbit.routing.name.product-quantity}")
    private String routingName;

    @Value("${ps.rabbit.exchange.name.product-quantity}")
    private String exchangeName;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(Long productId, Integer count) {
        StockDTO stock = new StockDTO(productId,count);
        System.out.println("Notification Sent ID : " + productId);
        rabbitTemplate.convertAndSend(exchangeName, routingName, stock);
    }
}
