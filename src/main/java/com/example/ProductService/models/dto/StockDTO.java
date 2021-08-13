package com.example.ProductService.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class StockDTO implements Serializable {
    private Long productId;
    private Integer quantity;
}
