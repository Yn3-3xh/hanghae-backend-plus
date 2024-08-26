package org.example.frameworkstudy.shop.category.repository;

import org.example.frameworkstudy.shop.category.domain.DetailCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DetailCategoryRepository extends CrudRepository<DetailCategory, Long> {

    Optional<DetailCategory> findByName(String subCategoryName);
}
