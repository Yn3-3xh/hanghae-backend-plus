package org.example.frameworkstudy.shop.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.example.frameworkstudy.shop.category.dto.response.ResponseDetailCategoryDto;
import org.example.frameworkstudy.shop.product.domain.Product;

@Getter
@Setter
@Builder
public class ResponseProductDto {
    private Long id;
    private String name;
    private int price;
    private int count;
    private String contents;
    private String manager;

    private ResponseDetailCategoryDto responseDetailCategoryDto;

    @Builder
    public static ResponseProductDto ofProduct(Product product) {
        return ResponseProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .count(product.getCount())
                .contents(product.getContents())
                .manager(product.getManager())
                .responseDetailCategoryDto(ResponseDetailCategoryDto.ofDetailCategory(product.getDetailCategory()))
                .build();
    }
}
