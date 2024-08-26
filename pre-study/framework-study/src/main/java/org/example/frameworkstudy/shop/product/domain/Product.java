package org.example.frameworkstudy.shop.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.example.frameworkstudy.shop.product.dto.RequestProductDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int count;

    private String contents;

    private String password;

    private String manager;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datail_category_id")
    private DetailCategory detailCategory;

    public void setDetailCategory(DetailCategory detailCategory) {
        if (this.detailCategory != null) {
            this.detailCategory.getProducts().remove(this);
        }

        this.detailCategory = detailCategory;
        if (detailCategory != null) {
            detailCategory.getProducts().add(this);
        }
    }

    @Builder
    public Product(Long id,
                   String name,
                   int price,
                   int count,
                   String contents,
                   String password,
                   String manager,
                   DetailCategory detailCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.contents = contents;
        this.password = password;
        this.manager = manager;
        this.detailCategory = detailCategory;
    }

    public void update(RequestProductDto requestProductDto) {
        this.name = requestProductDto.getName();
        this.price = requestProductDto.getPrice();
        this.count = requestProductDto.getCount();
        this.contents = requestProductDto.getContents();
    }
}
