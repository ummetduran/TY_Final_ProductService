package com.example.ProductService.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private Double oldPrice;
    private Double newPrice;

}
