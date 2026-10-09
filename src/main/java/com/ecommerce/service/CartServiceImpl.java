package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.dto.CartItemRequestDto;
import com.ecommerce.dto.CartItemResponseDto;
import com.ecommerce.dto.CartRequestDto;
import com.ecommerce.dto.CartResponseDto;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
			ProductRepository productRepository, UserRepository userRepository) {

		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	@Override
	public CartResponseDto createCart(CartRequestDto request) {

		Cart cart = mapToEntity(request);

		Cart savedCart = cartRepository.save(cart);

		return mapToDto(savedCart);
	}

	@Override
	public List<CartResponseDto> getAllCarts() {

		return cartRepository.findAll().stream().map(this::mapToDto).toList();
	}

	@Override
	public CartResponseDto getCartById(Long id) {

		Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

		return mapToDto(cart);
	}

	@Override
	public CartItemResponseDto addProductToCart(Long cartId, CartItemRequestDto request) {

		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		CartItem cartItem = CartItem.builder().cart(cart).product(product).quantity(request.getQuantity()).build();

		CartItem savedItem = cartItemRepository.save(cartItem);

		return mapToCartItemDto(savedItem);
	}

	@Override
	public List<CartItemResponseDto> getCartItems(Long cartId) {

		return cartItemRepository.findByCartId(cartId).stream().map(this::mapToCartItemDto).toList();
	}

	@Override
	public void removeCartItem(Long cartItemId) {

		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

		cartItemRepository.delete(cartItem);
	}

	private CartResponseDto mapToDto(Cart cart) {

		return CartResponseDto.builder().id(cart.getId()).userId(cart.getUser().getId()).build();
	}

	private Cart mapToEntity(CartRequestDto request) {

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return Cart.builder().user(user).build();
	}

	private CartItemResponseDto mapToCartItemDto(CartItem cartItem) {

		return CartItemResponseDto.builder().id(cartItem.getId()).cartId(cartItem.getCart().getId())
				.productId(cartItem.getProduct().getId()).quantity(cartItem.getQuantity()).build();
	}
}