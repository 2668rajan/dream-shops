package com.rajan.dreamshops.request;

import com.rajan.dreamshops.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String descriptions;
    private Category category;
}
