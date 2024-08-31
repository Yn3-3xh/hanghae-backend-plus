package org.example.frameworkstudy.shop.category.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.Category;
import org.example.frameworkstudy.shop.category.domain.SubCategory;

@Getter
@Setter
public class SubCategoryRequestDto {

    private Long id;
    private String name;
    private String manager;

    private Long categoryId;

    public SubCategory toSubCategory(Category category) {
        return SubCategory.builder()
                .id(this.id)
                .name(this.name)
                .manager(this.manager)
                .category(category)
                .build();
    }
}
