package org.example.frameworkstudy.shop.category.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.dto.request.RequestCategoryDto;
import org.example.frameworkstudy.shop.category.dto.response.ResponseCategoryDto;
import org.example.frameworkstudy.shop.category.service.CategoryService;
import org.example.frameworkstudy.common.controller.BaseApiControllerV1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController extends BaseApiControllerV1 {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseCategoryDto createCategory(@RequestBody RequestCategoryDto requestCategoryDto) {
        return categoryService.createCategory(requestCategoryDto);
    }
}
