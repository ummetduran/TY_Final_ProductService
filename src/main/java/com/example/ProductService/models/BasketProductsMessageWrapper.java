package com.example.ProductService.models;

import com.example.ProductService.models.dto.BasketProductsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketProductsMessageWrapper implements Serializable {
    BasketProductsDTO productsDTO;
    String type;

}
