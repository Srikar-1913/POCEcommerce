package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

    private Long userId;

    private Double totalAmount;

    private String orderStatus;
}