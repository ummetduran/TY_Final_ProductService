package com.example.ProductService.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockMessageConfig {
    @Value("${ps.rabbit.queue.name.product-quantity}")
    private String queueName;

    @Value("${ps.rabbit.routing.name.product-quantity}")
    private String routingName;
    @Value("${ps.rabbit.exchange.name.product-quantity}")
    private String exchangeName;

    @Bean
    public Queue queue(){
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(final Queue queue, final DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingName);
    }
}
