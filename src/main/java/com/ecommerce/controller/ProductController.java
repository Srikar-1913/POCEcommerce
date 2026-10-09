package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductRequestDto;
import com.ecommerce.dto.ProductResponseDto;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {

		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto request) {

		return new ResponseEntity<>(productService.addProduct(request), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {

		return ResponseEntity.ok(productService.getProductById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
			@RequestBody ProductRequestDto request) {

		return ResponseEntity.ok(productService.updateProduct(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

		productService.deleteProduct(id);

		return ResponseEntity.ok("Product deleted successfully");
	}
}