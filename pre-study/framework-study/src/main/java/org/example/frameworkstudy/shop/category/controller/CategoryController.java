package org.example.frameworkstudy.shop.category.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.dto.request.CategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.CategoryResponseDto;
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
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.createCategory(categoryRequestDto);
    }
}
