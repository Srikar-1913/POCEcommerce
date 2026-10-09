package com.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartItemResponseDto {

    private Long id;

    private Long cartId;

    private Long productId;

    private Integer quantity;
}