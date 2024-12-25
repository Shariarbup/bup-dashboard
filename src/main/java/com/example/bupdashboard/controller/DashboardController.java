package com.example.bupdashboard.controller;

import com.example.bupdashboard.entity.Category;
import com.example.bupdashboard.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboards")
public class DashboardController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showDashboardPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "dashboard/bup-dashboard";
    }

    @GetMapping("/search")
    public String searchLinks(@RequestParam("query") String query, Model model) {
        // Fetch categories and filter links based on the search query
        List<Category> categories = categoryService.getAllCategoriesWithFilteredLinks(query);

        categories.forEach(category -> {
            System.out.println("cat ca"+category.toString());
        });
        model.addAttribute("categories", categories);
//        model.addAttribute("query", query);
        // Add query to the model
//        return "dashboard/bup-dashboard";
        // Thymeleaf template name
        return "dashboard/bup-dashboard :: #accordion-container";
    }
}
