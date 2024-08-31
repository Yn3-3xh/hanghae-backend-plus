package org.example.frameworkstudy.shop.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.Category;
import org.example.frameworkstudy.shop.category.dto.request.CategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.CategoryResponseDto;
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
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        validateCreateRequest(categoryRequestDto);

        Category category = createCategoryFromDto(categoryRequestDto);
        return CategoryResponseDto.ofCategory(category);
    }

    private void validateCreateRequest(CategoryRequestDto categoryRequestDto) {
        checkCategoryNameDuplicated(categoryRequestDto.getName());
    }

    private void checkCategoryNameDuplicated(String categoryName) {
        categoryRepository.findByName(categoryName)
                .ifPresent(category -> {
                    throw new IllegalArgumentException("Category name must not be duplicated. [Category name] : " + categoryName);
                });
    }

    private Category createCategoryFromDto(CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestDto.toCategory();
        return categoryRepository.save(category);
    }
}
