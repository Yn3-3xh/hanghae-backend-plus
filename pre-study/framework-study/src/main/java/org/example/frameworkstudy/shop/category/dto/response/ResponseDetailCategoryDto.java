package org.example.frameworkstudy.shop.category.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;

@Getter
@Setter
@Builder
public class ResponseDetailCategoryDto {

    private Long id;
    private String name;
    private String manager;

    private ResponseSubCategoryDto responseSubCategoryDto;

    public static ResponseDetailCategoryDto ofDetailCategory(DetailCategory detailCategory) {
        return ResponseDetailCategoryDto.builder()
                .id(detailCategory.getId())
                .name(detailCategory.getName())
                .manager(detailCategory.getManager())
                .responseSubCategoryDto(ResponseSubCategoryDto.ofSubCategory(detailCategory.getSubCategory()))
                .build();
    }
}
