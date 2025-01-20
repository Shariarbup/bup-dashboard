package com.example.bupdashboard.controller;

import com.example.bupdashboard.dashboardDb.entity.Category;
import com.example.bupdashboard.dashboardDb.entity.Link;
import com.example.bupdashboard.dashboardDb.repository.CategoryRepository;
import com.example.bupdashboard.dashboardDb.repository.LinkRepository;
import com.example.bupdashboard.service.CategoryService;
import com.example.bupdashboard.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LinkService linkService;

    @GetMapping("/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/new";
    }

    @PostMapping("/new")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("noResults", categoryService.getAllCategories().isEmpty());
        return "categories/list";
    }

    @GetMapping("/{categoryId}")
    public String getCategoryLinks(@PathVariable("categoryId") Long categoryId, Model model) {
        // Find the category by its ID
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            // Get all the links associated with the category
            List<Link> links = new ArrayList<>(category.getLinks());
            model.addAttribute("category", category);
            model.addAttribute("links", links);
        } else {
            // If the category is not found, redirect to an error page or category list
            return "redirect:/categories";
        }
        return "categories/category-links"; // Return the name of the view to show the links
    }

    @GetMapping("/{id}/edit")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "categories/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("category") Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/categories"; // Redirect to the category list page
    }

    @GetMapping("/search")
    public String searchCategories(@RequestParam("query") String query, Model model) {
        // Fetch categories and filter links based on the search query
        List<Category> categories = categoryService.getCategoryByName(query);

        model.addAttribute("categories", categories);
        model.addAttribute("noResults", categories.isEmpty()); // Add a flag for empty results
        return "categories/list :: #category-container";
    }
}
