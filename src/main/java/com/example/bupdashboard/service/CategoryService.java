package com.example.bupdashboard.service;

import com.example.bupdashboard.dashboardDb.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category saveCategory(Category category);

    Category findById(Long id);

    void updateCategory(Long id, Category category);

    List<Category> getAllCategoriesWithFilteredLinks(String query);

    List<Category> getCategoryByName(String query);
}
