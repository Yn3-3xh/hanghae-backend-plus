package org.example.frameworkstudy.shop.category.controller;

import lombok.RequiredArgsConstructor;
import org.example.frameworkstudy.shop.category.dto.request.DetailCategoryRequestDto;
import org.example.frameworkstudy.shop.category.dto.response.DetailCategoryResponseDto;
import org.example.frameworkstudy.shop.category.service.DetailCategoryService;
import org.example.frameworkstudy.common.controller.BaseApiControllerV1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detail-categories")
@RequiredArgsConstructor
public class DetailCategoryController extends BaseApiControllerV1 {

    private final DetailCategoryService detailCategoryService;

    @PostMapping
    public DetailCategoryResponseDto createDetailCategory(@RequestBody DetailCategoryRequestDto detailCategoryRequestDto) {
        return detailCategoryService.createDetailCategory(detailCategoryRequestDto);
    }
}
