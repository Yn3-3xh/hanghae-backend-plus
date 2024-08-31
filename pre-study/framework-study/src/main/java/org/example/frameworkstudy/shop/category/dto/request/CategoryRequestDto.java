package org.example.frameworkstudy.shop.category.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.Category;

@Getter
@Setter
public class CategoryRequestDto {

    private Long id;
    private String name;
    private String manager;

    public Category toCategory() {
        return Category.builder()
                .id(this.id)
                .name(this.name)
                .manager(this.manager)
                .build();
    }
}
