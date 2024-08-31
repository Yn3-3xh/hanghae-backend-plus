package org.example.frameworkstudy.shop.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.common.controller.BaseApiControllerV1;
import org.example.frameworkstudy.shop.product.dto.ProductRequestDto;
import org.example.frameworkstudy.shop.product.dto.ProductResponseDto;
import org.example.frameworkstudy.shop.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController extends BaseApiControllerV1 {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductResponseDto> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long id,
                                            @RequestBody ProductRequestDto productRequestDto) {
        return productService.updateProduct(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id,
                                              @RequestBody ProductRequestDto productRequestDto) {
        productService.deleteProduct(id, productRequestDto);
        return ResponseEntity.noContent().build();
    }
}
