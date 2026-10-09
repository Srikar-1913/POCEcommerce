package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CartItemRequestDto;
import com.ecommerce.dto.CartItemResponseDto;
import com.ecommerce.dto.CartRequestDto;
import com.ecommerce.dto.CartResponseDto;
import com.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping
	public ResponseEntity<CartResponseDto> createCart(@RequestBody CartRequestDto request) {

		return new ResponseEntity<>(cartService.createCart(request), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CartResponseDto>> getAllCarts() {

		return ResponseEntity.ok(cartService.getAllCarts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CartResponseDto> getCartById(@PathVariable Long id) {

		return ResponseEntity.ok(cartService.getCartById(id));
	}

	@PostMapping("/{cartId}/items")
	public ResponseEntity<CartItemResponseDto> addProductToCart(@PathVariable Long cartId,
			@RequestBody CartItemRequestDto request) {

		return ResponseEntity.ok(cartService.addProductToCart(cartId, request));
	}

	public ResponseEntity<List<CartItemResponseDto>> getCartItems(@PathVariable Long cartId) {

		return ResponseEntity.ok(cartService.getCartItems(cartId));
	}

	@DeleteMapping("/items/{cartItemId}")
	public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {

		cartService.removeCartItem(cartItemId);

		return ResponseEntity.ok("Cart item removed successfully");
	}
}