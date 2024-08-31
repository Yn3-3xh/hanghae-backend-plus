package org.example.frameworkstudy.shop.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.Category;
import org.example.frameworkstudy.shop.category.repository.CategoryRepository;
import org.example.frameworkstudy.shop.category.service.SubCategoryService;
import org.example.frameworkstudy.shop.category.domain.SubCategory;
import org.example.frameworkstudy.shop.category.dto.request.SubCategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.SubCategoryResponseDto;
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
    public SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        validateCreateRequest(subCategoryRequestDto);

        Category category = findCategory(subCategoryRequestDto.getCategoryId());
        SubCategory subCategory = createSubCategoryFromDto(subCategoryRequestDto, category);
        return SubCategoryResponseDto.ofSubCategory(subCategory);
    }

    private void validateCreateRequest(SubCategoryRequestDto subCategoryRequestDto) {
        if (subCategoryRequestDto.getCategoryId() == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        checkSubCategoryNameDuplicated(subCategoryRequestDto.getName());
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

    private SubCategory createSubCategoryFromDto(SubCategoryRequestDto subCategoryRequestDto, Category category) {
        SubCategory subCategory = subCategoryRequestDto.toSubCategory(category);
        return subCategoryRepository.save(subCategory);
    }
}
