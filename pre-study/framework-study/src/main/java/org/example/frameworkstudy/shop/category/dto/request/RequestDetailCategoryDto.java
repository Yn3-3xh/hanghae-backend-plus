package org.example.frameworkstudy.shop.category.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.example.frameworkstudy.shop.category.domain.SubCategory;

@Getter
@Setter
public class RequestDetailCategoryDto {

    private Long id;
    private String name;
    private String manager;

    private Long subCategoryId;

    public DetailCategory toDetailCategory(SubCategory subCategory) {
        return DetailCategory.builder()
                .id(this.id)
                .name(this.name)
                .manager(this.manager)
                .subCategory(subCategory)
                .build();
    }
}
