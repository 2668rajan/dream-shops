package com.rajan.dreamshops.dto;

import com.rajan.dreamshops.entity.Category;
import com.rajan.dreamshops.entity.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String descriptions;
    private Category category;
    private List<ImageDto> images;

}
