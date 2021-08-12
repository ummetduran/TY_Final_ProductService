package com.example.ProductService.amqp.config;

import com.example.ProductService.amqp.ProductPriceChangeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class Producer {
    @Value("${ps.rabbit.routing.name}")
    private String routingName;

    @Value("${ps.rabbit.exchange.name}")
    private String exchangeName;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(ProductPriceChangeMessage productPriceChangeMessage) {

        System.out.println("Notification Sent ID : " + productPriceChangeMessage);
        rabbitTemplate.convertAndSend(exchangeName, routingName, productPriceChangeMessage);
    }
}
