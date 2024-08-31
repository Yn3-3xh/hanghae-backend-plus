package org.example.frameworkstudy.shop.category.service;

import org.example.frameworkstudy.shop.category.dto.request.CategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.CategoryResponseDto;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

}
