package com.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponseDto {

	private Long id;

	private Long userId;

	private Double totalAmount;

	private String orderStatus;
}
