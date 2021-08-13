package com.example.ProductService.models;

import com.example.ProductService.models.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProductPriceChangeMessage implements Serializable {
    private String id;
    ProductDTO message;



}
