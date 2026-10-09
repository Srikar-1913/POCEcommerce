package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.CartItemRequestDto;
import com.ecommerce.dto.CartItemResponseDto;
import com.ecommerce.dto.CartRequestDto;
import com.ecommerce.dto.CartResponseDto;

public interface CartService {

	CartResponseDto createCart(CartRequestDto request);

	List<CartResponseDto> getAllCarts();

	CartResponseDto getCartById(Long id);

	CartItemResponseDto addProductToCart(Long cartId, CartItemRequestDto request);

	List<CartItemResponseDto> getCartItems(Long cartId);

	void removeCartItem(Long cartItemId);
}