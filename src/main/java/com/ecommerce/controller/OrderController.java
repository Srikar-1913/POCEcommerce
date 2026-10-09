package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.OrderRequestDto;
import com.ecommerce.dto.OrderResponseDto;
import com.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {

		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto request) {

		return new ResponseEntity<>(orderService.placeOrder(request), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<OrderResponseDto>> getAllOrders() {

		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {

		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable Long userId) {

		return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
	}
}