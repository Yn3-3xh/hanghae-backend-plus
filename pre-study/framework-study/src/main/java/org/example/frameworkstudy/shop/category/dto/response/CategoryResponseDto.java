package org.example.frameworkstudy.shop.category.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.Category;

@Getter
@Setter
@Builder
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String manager;

    public static CategoryResponseDto ofCategory(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .manager(category.getManager())
                .build();
    }
}
