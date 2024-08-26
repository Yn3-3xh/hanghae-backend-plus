package org.example.frameworkstudy.shop.category.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.frameworkstudy.common.entity.AbstractAuditable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubCategory extends AbstractAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<DetailCategory> detailCategories = new ArrayList<>();

    public void setCategory(Category category) {
        if (this.category != null) {
            this.category.getSubCategories().remove(this);
        }

        this.category = category;
        if (category != null) {
            category.getSubCategories().add(this);
        }
    }

    @Builder
    public SubCategory(Long id,
                       String name,
                       String manager,
                       Category category) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.category = category;
    }
}
