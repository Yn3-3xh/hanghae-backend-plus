package org.example.frameworkstudy.shop.category.service;

import org.example.frameworkstudy.shop.category.dto.request.RequestCategoryDto;
import org.example.frameworkstudy.shop.category.dto.response.ResponseCategoryDto;

public interface CategoryService {

    ResponseCategoryDto createCategory(RequestCategoryDto requestCategoryDto);

}
