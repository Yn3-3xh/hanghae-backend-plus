package org.example.frameworkstudy.shop.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.common.controller.BaseApiControllerV1;
import org.example.frameworkstudy.shop.product.dto.RequestProductDto;
import org.example.frameworkstudy.shop.product.dto.ResponseProductDto;
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
    public ResponseProductDto getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ResponseProductDto> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public ResponseProductDto createProduct(@RequestBody RequestProductDto requestProductDto) {
        return productService.createProduct(requestProductDto);
    }

    @PatchMapping("/{id}")
    public ResponseProductDto updateProduct(@PathVariable("id") Long id,
                                            @RequestBody RequestProductDto requestProductDto) {
        return productService.updateProduct(id, requestProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id,
                                              @RequestBody RequestProductDto requestProductDto) {
        productService.deleteProduct(id, requestProductDto);
        return ResponseEntity.noContent().build();
    }
}
