package org.example.frameworkstudy.shop.category.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.SubCategory;

@Getter
@Setter
@Builder
public class ResponseSubCategoryDto {

    private Long id;
    private String name;
    private String manager;

    private ResponseCategoryDto responseCategoryDto;

    public static ResponseSubCategoryDto ofSubCategory(SubCategory subCategory) {
        return ResponseSubCategoryDto.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .manager(subCategory.getManager())
                .responseCategoryDto(ResponseCategoryDto.ofCategory(subCategory.getCategory()))
                .build();
    }
}
