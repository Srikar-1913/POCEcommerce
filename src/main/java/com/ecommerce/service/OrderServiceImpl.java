package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.dto.OrderRequestDto;
import com.ecommerce.dto.OrderResponseDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;

	public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {

		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	@Override
	public OrderResponseDto placeOrder(OrderRequestDto request) {

		Order order = mapToEntity(request);

		Order savedOrder = orderRepository.save(order);

		return mapToDto(savedOrder);
	}

	@Override
	public List<OrderResponseDto> getAllOrders() {

		return orderRepository.findAll().stream().map(this::mapToDto).toList();
	}

	@Override
	public OrderResponseDto getOrderById(Long id) {

		Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

		return mapToDto(order);
	}

	@Override
	public List<OrderResponseDto> getOrdersByUserId(Long userId) {

		return orderRepository.findByUser_Id(userId).stream().map(this::mapToDto).toList();
	}

	private OrderResponseDto mapToDto(Order order) {

		return OrderResponseDto.builder().id(order.getId()).userId(order.getUser().getId())
				.totalAmount(order.getTotalAmount()).orderStatus(order.getOrderStatus()).build();
	}

	private Order mapToEntity(OrderRequestDto request) {

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return Order.builder().user(user).totalAmount(request.getTotalAmount()).orderStatus(request.getOrderStatus())
				.build();
	}
}