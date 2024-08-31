package org.example.frameworkstudy.shop.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.example.frameworkstudy.shop.product.domain.Product;

@Getter
@Setter
public class ProductRequestDto {

    private String name;
    private int price;
    private int count;
    private String contents;
    private String password;
    private String manager;

    private Long detailCategoryId;

    public Product toProduct(DetailCategory detailCategory) {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .count(this.count)
                .contents(this.contents)
                .password(this.password)
                .manager(this.manager)
                .detailCategory(detailCategory)
                .build();
    }
}
