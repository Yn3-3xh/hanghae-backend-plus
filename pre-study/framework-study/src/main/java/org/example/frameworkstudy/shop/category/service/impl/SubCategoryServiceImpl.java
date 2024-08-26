package org.example.frameworkstudy.shop.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.Category;
import org.example.frameworkstudy.shop.category.repository.CategoryRepository;
import org.example.frameworkstudy.shop.category.service.SubCategoryService;
import org.example.frameworkstudy.shop.category.domain.SubCategory;
import org.example.frameworkstudy.shop.category.dto.request.RequestSubCategoryDto;
import org.example.frameworkstudy.shop.category.dto.response.ResponseSubCategoryDto;
import org.example.frameworkstudy.shop.category.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public ResponseSubCategoryDto createSubCategory(RequestSubCategoryDto requestSubCategoryDto) {
        validateCreateRequest(requestSubCategoryDto);

        Category category = findCategory(requestSubCategoryDto.getCategoryId());
        SubCategory subCategory = createSubCategoryFromDto(requestSubCategoryDto, category);
        return ResponseSubCategoryDto.ofSubCategory(subCategory);
    }

    private void validateCreateRequest(RequestSubCategoryDto requestSubCategoryDto) {
        if (requestSubCategoryDto.getCategoryId() == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        checkSubCategoryNameDuplicated(requestSubCategoryDto.getName());
    }

    private void checkSubCategoryNameDuplicated(String subCategoryName) {
        subCategoryRepository.findByName(subCategoryName)
                .ifPresent(subCategory -> {
                    throw new IllegalArgumentException("Sub category name must not be duplicated. [Sub category name] : " + subCategoryName);
                });
    }

    private Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category id : " + categoryId));
    }

    private SubCategory createSubCategoryFromDto(RequestSubCategoryDto requestSubCategoryDto, Category category) {
        SubCategory subCategory = requestSubCategoryDto.toSubCategory(category);
        return subCategoryRepository.save(subCategory);
    }
}
