package org.example.frameworkstudy.shop.category.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.dto.request.SubCategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.SubCategoryResponseDto;
import org.example.frameworkstudy.shop.category.service.SubCategoryService;
import org.example.frameworkstudy.common.controller.BaseApiControllerV1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sub-categories")
@RequiredArgsConstructor
public class SubCategoryController extends BaseApiControllerV1 {

    private final SubCategoryService subCategoryService;

    @PostMapping
    public SubCategoryResponseDto createSubCategory(@RequestBody SubCategoryRequestDto subCategoryRequestDto) {
        return subCategoryService.createSubCategory(subCategoryRequestDto);
    }
}
