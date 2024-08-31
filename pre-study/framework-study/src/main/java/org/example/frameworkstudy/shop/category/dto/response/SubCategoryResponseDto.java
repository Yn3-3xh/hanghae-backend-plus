package org.example.frameworkstudy.shop.category.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.SubCategory;

@Getter
@Setter
@Builder
public class SubCategoryResponseDto {

    private Long id;
    private String name;
    private String manager;

    private CategoryResponseDto categoryResponseDto;

    public static SubCategoryResponseDto ofSubCategory(SubCategory subCategory) {
        return SubCategoryResponseDto.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .manager(subCategory.getManager())
                .categoryResponseDto(CategoryResponseDto.ofCategory(subCategory.getCategory()))
                .build();
    }
}
