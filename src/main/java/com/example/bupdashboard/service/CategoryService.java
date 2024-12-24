package com.example.bupdashboard.service;

import com.example.bupdashboard.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category saveCategory(Category category);
}
