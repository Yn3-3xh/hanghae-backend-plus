package org.example.frameworkstudy.shop.category.service;

import org.example.frameworkstudy.shop.category.dto.request.SubCategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.SubCategoryResponseDto;

public interface SubCategoryService {

    SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto);
}
