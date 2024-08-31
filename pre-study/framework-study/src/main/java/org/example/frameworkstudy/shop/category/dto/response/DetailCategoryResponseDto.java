package org.example.frameworkstudy.shop.category.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;

@Getter
@Setter
@Builder
public class DetailCategoryResponseDto {

    private Long id;
    private String name;
    private String manager;

    private SubCategoryResponseDto subCategoryResponseDto;

    public static DetailCategoryResponseDto ofDetailCategory(DetailCategory detailCategory) {
        return DetailCategoryResponseDto.builder()
                .id(detailCategory.getId())
                .name(detailCategory.getName())
                .manager(detailCategory.getManager())
                .subCategoryResponseDto(SubCategoryResponseDto.ofSubCategory(detailCategory.getSubCategory()))
                .build();
    }
}
