package org.example.frameworkstudy.shop.category.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.frameworkstudy.common.entity.AbstractAuditable;
import org.example.frameworkstudy.shop.product.domain.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailCategory extends AbstractAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "detailCategory", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public void setSubCategory(SubCategory subCategory) {
        if (this.subCategory != null) {
            this.subCategory.getDetailCategories().remove(this);
        }

        this.subCategory = subCategory;
        if (subCategory != null) {
            subCategory.getDetailCategories().add(this);
        }
    }

    @Builder
    public DetailCategory(Long id,
                          String name,
                          String manager,
                          SubCategory subCategory) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.subCategory = subCategory;
    }
}
