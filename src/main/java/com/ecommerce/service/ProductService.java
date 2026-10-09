package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ProductRequestDto;
import com.ecommerce.dto.ProductResponseDto;

public interface ProductService {

	ProductResponseDto addProduct(ProductRequestDto request);

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto getProductById(Long id);

	ProductResponseDto updateProduct(Long id, ProductRequestDto request);

	void deleteProduct(Long id);
}