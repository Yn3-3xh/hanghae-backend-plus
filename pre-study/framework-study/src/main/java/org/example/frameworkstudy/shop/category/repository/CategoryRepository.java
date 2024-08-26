package org.example.frameworkstudy.shop.category.repository;

import org.example.frameworkstudy.shop.category.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByName(String categoryName);
}
