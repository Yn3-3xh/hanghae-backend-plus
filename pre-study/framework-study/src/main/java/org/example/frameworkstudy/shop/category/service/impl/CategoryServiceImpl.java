package org.example.frameworkstudy.shop.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.Category;
import org.example.frameworkstudy.shop.category.dto.request.RequestCategoryDto;
import org.example.frameworkstudy.shop.category.dto.response.ResponseCategoryDto;
import org.example.frameworkstudy.shop.category.repository.CategoryRepository;
import org.example.frameworkstudy.shop.category.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ResponseCategoryDto createCategory(RequestCategoryDto requestCategoryDto) {
        validateCreateRequest(requestCategoryDto);

        Category category = createCategoryFromDto(requestCategoryDto);
        return ResponseCategoryDto.ofCategory(category);
    }

    private void validateCreateRequest(RequestCategoryDto requestCategoryDto) {
        checkCategoryNameDuplicated(requestCategoryDto.getName());
    }

    private void checkCategoryNameDuplicated(String categoryName) {
        categoryRepository.findByName(categoryName)
                .ifPresent(category -> {
                    throw new IllegalArgumentException("Category name must not be duplicated. [Category name] : " + categoryName);
                });
    }

    private Category createCategoryFromDto(RequestCategoryDto requestCategoryDto) {
        Category category = requestCategoryDto.toCategory();
        return categoryRepository.save(category);
    }
}
