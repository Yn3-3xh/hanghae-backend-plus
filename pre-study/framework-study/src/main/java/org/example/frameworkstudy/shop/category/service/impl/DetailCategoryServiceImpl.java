package org.example.frameworkstudy.shop.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.example.frameworkstudy.shop.category.domain.SubCategory;
import org.example.frameworkstudy.shop.category.dto.request.RequestDetailCategoryDto;
import org.example.frameworkstudy.shop.category.dto.response.ResponseDetailCategoryDto;
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
    public ResponseDetailCategoryDto createDetailCategory(RequestDetailCategoryDto requestDetailCategoryDto) {
        validateCreateRequest(requestDetailCategoryDto);

        SubCategory subCategory = findSubCategory(requestDetailCategoryDto.getSubCategoryId());
        DetailCategory detailCategory = createSubCategoryFromDto(requestDetailCategoryDto, subCategory);
        return ResponseDetailCategoryDto.ofDetailCategory(detailCategory);
    }

    private void validateCreateRequest(RequestDetailCategoryDto requestDetailCategoryDto) {
        if (requestDetailCategoryDto.getSubCategoryId() == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        checkDetailCategoryNameDuplicated(requestDetailCategoryDto.getName());
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

    private DetailCategory createSubCategoryFromDto(RequestDetailCategoryDto requestDetailCategoryDto, SubCategory subCategory) {
        DetailCategory detailCategory = requestDetailCategoryDto.toDetailCategory(subCategory);
        return detailCategoryRepository.save(detailCategory);
    }
}
