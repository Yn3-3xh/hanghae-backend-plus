package org.example.frameworkstudy.shop.product.service;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.example.frameworkstudy.shop.category.repository.DetailCategoryRepository;
import org.example.frameworkstudy.shop.product.domain.Product;
import org.example.frameworkstudy.shop.product.dto.ProductRequestDto;
import org.example.frameworkstudy.shop.product.dto.ProductResponseDto;
import org.example.frameworkstudy.shop.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final DetailCategoryRepository detailCategoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        validateCreateRequest(productRequestDto);

        DetailCategory detailCategory = findDetailCategory(productRequestDto.getDetailCategoryId());
        Product product = createProductFromDto(productRequestDto, detailCategory);
        return ProductResponseDto.ofProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(Long id) {
        Product product = findProduct(id);
        return ProductResponseDto.ofProduct(product);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        validateUpdateRequest(id, productRequestDto.getDetailCategoryId());

        Product product = findProduct(id);
        checkPasswordCorrect(productRequestDto.getPassword(), product.getPassword());

        if (isDetailCategoryUpdate(id, productRequestDto.getDetailCategoryId())) {
            DetailCategory detailCategory = findDetailCategory(productRequestDto.getDetailCategoryId());
            product.setDetailCategory(detailCategory);
        }

        product.update(productRequestDto);
        return ProductResponseDto.ofProduct(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id, ProductRequestDto productRequestDto) {
        validateDeleteRequest(id);

        Product product = findProduct(id);
        checkPasswordCorrect(productRequestDto.getPassword(), product.getPassword());

        deleteProductById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts() {
        List<Product> products = findProducts();
        return convProductsToResponses(products);
    }

    private void validateCreateRequest(ProductRequestDto productRequestDto) {
        if (productRequestDto.getDetailCategoryId() == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        checkProductNameDuplicated(productRequestDto.getName());
    }

    private void validateUpdateRequest(Long productId, Long detailCategoryId) {
        if (productId == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        if (detailCategoryId == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }
    }

    private void validateDeleteRequest(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }
    }

    private void checkProductNameDuplicated(String productName) {
        productRepository.findByName(productName)
                .ifPresent(subCategory -> {
                    throw new IllegalArgumentException("Product name must not be duplicated. [Product name] : " + productName);
                });
    }

    private DetailCategory findDetailCategory(Long detailCategoryId) {
        return detailCategoryRepository.findById(detailCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid detail category id : " + detailCategoryId));
    }

    private Product createProductFromDto(ProductRequestDto productRequestDto, DetailCategory detailCategory) {
        Product product = productRequestDto.toProduct(detailCategory);
        return productRepository.save(product);
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id : " + productId));
    }

    private List<Product> findProducts() {
        return productRepository.findAllByOrderByLatestDate();
    }

    private void checkPasswordCorrect(String passwordReq, String passwordRes) {
        if (!passwordReq.equals(passwordRes)) {
            throw new IllegalArgumentException("Password does not match. [Entered password] : " + passwordReq);
        }
    }

    private boolean isDetailCategoryUpdate(Long idReq, Long idRes) {
        return !idReq.equals(idRes);
    }

    private void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private List<ProductResponseDto> convProductsToResponses(List<Product> products) {
        return products.stream()
                .map(ProductResponseDto::ofProduct)
                .toList();
    }
}
