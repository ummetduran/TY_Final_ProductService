package com.example.ProductService.amqp;

import com.example.ProductService.models.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Message implements Serializable {
    private String id;
    ProductDTO message;



}
