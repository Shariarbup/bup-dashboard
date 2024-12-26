package com.example.bupdashboard.service.impl;

import com.example.bupdashboard.entity.Category;
import com.example.bupdashboard.entity.Link;
import com.example.bupdashboard.repository.CategoryRepository;
import com.example.bupdashboard.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return findCategoryById(id);
    }

    @Override
    public void updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = findById(id);
        existingCategory.setName(updatedCategory.getName());
        categoryRepository.save(existingCategory);
    }

    @Override
    public List<Category> getAllCategoriesWithFilteredLinks(String query) {
        return categoryRepository.findAll().stream()
                .peek(category -> {
                    // Filter links for each category
                    List<Link> filteredLinks = category.getLinks().stream()
                            .filter(link -> link.getName().toLowerCase().contains(query.toLowerCase()))
                            .collect(Collectors.toList());
                    category.setLinks(filteredLinks); // Update category with filtered links
                })
                .filter(category -> !category.getLinks().isEmpty()) // Keep only categories with links
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getCategoryByName(String query) {
        return categoryRepository.findByNameContainingIgnoreCase(query);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
    }
}
