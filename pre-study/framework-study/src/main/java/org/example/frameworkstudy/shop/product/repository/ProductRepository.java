package org.example.frameworkstudy.shop.product.repository;

import org.example.frameworkstudy.shop.product.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByName(String productName);

    @Query("SELECT p FROM Product p ORDER BY GREATEST(p.updatedAt, p.createdAt) DESC")
    List<Product> findAllByOrderByLatestDate();
}
