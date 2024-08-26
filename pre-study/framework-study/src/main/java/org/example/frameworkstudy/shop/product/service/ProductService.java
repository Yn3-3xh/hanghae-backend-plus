package org.example.frameworkstudy.shop.product.service;

import org.example.frameworkstudy.shop.product.dto.RequestProductDto;
import org.example.frameworkstudy.shop.product.dto.ResponseProductDto;

import java.util.List;

public interface ProductService {

    ResponseProductDto createProduct(RequestProductDto requestProductDto);

    ResponseProductDto getProduct(Long id);

    ResponseProductDto updateProduct(Long id, RequestProductDto requestProductDto);

    void deleteProduct(Long id, RequestProductDto requestProductDto);

    List<ResponseProductDto> getProducts();
}
