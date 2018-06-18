package com.nju.category_service.dao;

import com.nju.category_service.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDAO extends JpaRepository<CategoryEntity, Integer> {

    @Query("select max(entity.id) from CategoryEntity entity")
    Integer findMaxId();
}
