package com.nju.category_service.service;

import com.nju.category_service.dao.CategoryDAO;
import com.nju.category_service.model.CategoryEntity;
import com.nju.category_service.restController.info.CategoryInfo;
import com.nju.category_service.restController.info.CategoryListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public CategoryInfo getCategory(Integer id) {
        CategoryEntity petCategoryEntity=categoryDAO.getOne(id);
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setId(petCategoryEntity.getId());
        categoryInfo.setName(petCategoryEntity.getCname());
        categoryInfo.setDept(petCategoryEntity.getDescription());
        return categoryInfo;
    }

    @Override
    public CategoryListInfo getCategories() {
        CategoryListInfo categoryListInfo = new CategoryListInfo();
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        List<CategoryEntity> petCategoryEntities = categoryDAO.findAll();
        for (CategoryEntity petCategoryEntity: petCategoryEntities){
            CategoryInfo categoryInfo = new CategoryInfo();
            categoryInfo.setId(petCategoryEntity.getId());
            categoryInfo.setName(petCategoryEntity.getCname());
            categoryInfo.setDept(petCategoryEntity.getDescription());
            categoryInfos.add(categoryInfo);
        }
        categoryListInfo.setCategoryInfos(categoryInfos);
        return categoryListInfo;
    }

    @Override
    public int addCategory(CategoryInfo categoryInfo) {
        Integer id = categoryInfo.getId();
        CategoryEntity petCategoryEntity = null;
        if (id == -1){
            id = categoryDAO.findMaxId();
            if (id == null) id = 0;
            id++;
            petCategoryEntity = new CategoryEntity();
            petCategoryEntity.setId(id);
        } else {
            petCategoryEntity = categoryDAO.getOne(id);
        }
        petCategoryEntity.setCname(categoryInfo.getName());
        petCategoryEntity.setDescription(categoryInfo.getDept());
        categoryDAO.save(petCategoryEntity);
        categoryDAO.flush();
        return 1;
    }
}
