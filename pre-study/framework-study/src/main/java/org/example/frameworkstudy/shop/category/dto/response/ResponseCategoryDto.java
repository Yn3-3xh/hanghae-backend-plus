package org.example.frameworkstudy.shop.category.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.Category;

@Getter
@Setter
@Builder
public class ResponseCategoryDto {

    private Long id;
    private String name;
    private String manager;

    public static ResponseCategoryDto ofCategory(Category category) {
        return ResponseCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .manager(category.getManager())
                .build();
    }
}
