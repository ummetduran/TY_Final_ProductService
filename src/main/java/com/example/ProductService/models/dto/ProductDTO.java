package com.example.ProductService.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long productId;
    private String productName;
    private Double oldPrice;
    private Double newPrice;

}
