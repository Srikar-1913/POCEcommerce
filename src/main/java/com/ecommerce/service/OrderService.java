package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderRequestDto;
import com.ecommerce.dto.OrderResponseDto;

public interface OrderService {

	OrderResponseDto placeOrder(OrderRequestDto request);

	List<OrderResponseDto> getAllOrders();

	OrderResponseDto getOrderById(Long id);

	List<OrderResponseDto> getOrdersByUserId(Long userId);
}