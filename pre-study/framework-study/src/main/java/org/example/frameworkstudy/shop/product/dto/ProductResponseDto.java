package org.example.frameworkstudy.shop.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.dto.response.DetailCategoryResponseDto;
import org.example.frameworkstudy.shop.product.domain.Product;

@Getter
@Setter
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private int count;
    private String contents;
    private String manager;

    private DetailCategoryResponseDto detailCategoryResponseDto;

    @Builder
    public static ProductResponseDto ofProduct(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .count(product.getCount())
                .contents(product.getContents())
                .manager(product.getManager())
                .detailCategoryResponseDto(DetailCategoryResponseDto.ofDetailCategory(product.getDetailCategory()))
                .build();
    }
}
