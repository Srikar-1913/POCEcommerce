package com.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private String category;

    private String imageUrl;
}