package org.example.frameworkstudy.shop.product.service;

import org.example.frameworkstudy.shop.product.dto.ProductRequestDto;
import org.example.frameworkstudy.shop.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto getProduct(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    void deleteProduct(Long id, ProductRequestDto productRequestDto);

    List<ProductResponseDto> getProducts();
}
