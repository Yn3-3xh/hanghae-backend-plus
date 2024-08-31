package org.example.frameworkstudy.shop.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.example.frameworkstudy.shop.category.domain.SubCategory;
import org.example.frameworkstudy.shop.category.dto.request.DetailCategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.DetailCategoryResponseDto;
import org.example.frameworkstudy.shop.category.repository.DetailCategoryRepository;
import org.example.frameworkstudy.shop.category.repository.SubCategoryRepository;
import org.example.frameworkstudy.shop.category.service.DetailCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DetailCategoryServiceImpl implements DetailCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final DetailCategoryRepository detailCategoryRepository;

    @Override
    @Transactional
    public DetailCategoryResponseDto createDetailCategory(DetailCategoryRequestDto detailCategoryRequestDto) {
        validateCreateRequest(detailCategoryRequestDto);

        SubCategory subCategory = findSubCategory(detailCategoryRequestDto.getSubCategoryId());
        DetailCategory detailCategory = createSubCategoryFromDto(detailCategoryRequestDto, subCategory);
        return DetailCategoryResponseDto.ofDetailCategory(detailCategory);
    }

    private void validateCreateRequest(DetailCategoryRequestDto detailCategoryRequestDto) {
        if (detailCategoryRequestDto.getSubCategoryId() == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        checkDetailCategoryNameDuplicated(detailCategoryRequestDto.getName());
    }

    private void checkDetailCategoryNameDuplicated(String detailCategoryName) {
        detailCategoryRepository.findByName(detailCategoryName)
                .ifPresent(subCategory -> {
                    throw new IllegalArgumentException("Detail category name must not be duplicated. [Detail category name] : " + detailCategoryName);
                });
    }

    private SubCategory findSubCategory(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sub category id : " + subCategoryId));
    }

    private DetailCategory createSubCategoryFromDto(DetailCategoryRequestDto detailCategoryRequestDto, SubCategory subCategory) {
        DetailCategory detailCategory = detailCategoryRequestDto.toDetailCategory(subCategory);
        return detailCategoryRepository.save(detailCategory);
    }
}
