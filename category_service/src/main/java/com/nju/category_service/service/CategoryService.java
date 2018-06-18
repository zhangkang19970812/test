package com.nju.category_service.service;

import com.nju.category_service.restController.info.CategoryInfo;
import com.nju.category_service.restController.info.CategoryListInfo;

public interface CategoryService {
    CategoryInfo getCategory(Integer id);
    CategoryListInfo getCategories();
    int addCategory(CategoryInfo categoryInfo);
}
