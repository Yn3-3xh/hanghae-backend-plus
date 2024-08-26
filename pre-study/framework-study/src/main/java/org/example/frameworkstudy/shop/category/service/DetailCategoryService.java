package org.example.frameworkstudy.shop.category.service;

import org.example.frameworkstudy.shop.category.dto.request.RequestDetailCategoryDto;
import org.example.frameworkstudy.shop.category.dto.response.ResponseDetailCategoryDto;

public interface DetailCategoryService {

    ResponseDetailCategoryDto createDetailCategory(RequestDetailCategoryDto requestDetailCategoryDto);
}
