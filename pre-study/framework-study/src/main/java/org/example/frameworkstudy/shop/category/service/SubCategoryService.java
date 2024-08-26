package org.example.frameworkstudy.shop.category.service;

import org.example.frameworkstudy.shop.category.dto.request.RequestSubCategoryDto;
import org.example.frameworkstudy.shop.category.dto.response.ResponseSubCategoryDto;

public interface SubCategoryService {

    ResponseSubCategoryDto createSubCategory(RequestSubCategoryDto requestSubCategoryDto);
}
