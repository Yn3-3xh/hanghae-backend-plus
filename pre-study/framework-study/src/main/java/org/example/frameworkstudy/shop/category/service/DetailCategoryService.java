package org.example.frameworkstudy.shop.category.service;

import org.example.frameworkstudy.shop.category.dto.request.DetailCategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.DetailCategoryResponseDto;

public interface DetailCategoryService {

    DetailCategoryResponseDto createDetailCategory(DetailCategoryRequestDto detailCategoryRequestDto);
}
