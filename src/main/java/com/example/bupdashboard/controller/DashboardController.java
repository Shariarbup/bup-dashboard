package com.example.bupdashboard.controller;

import com.example.bupdashboard.dashboardDb.entity.Category;
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

        model.addAttribute("categories", categories);
        model.addAttribute("noResults", categories.isEmpty()); // Add a flag for empty results
        return "dashboard/category-search-result :: #accordion-container";
    }

    @GetMapping("/search/new")
    public String searchLinksFromHomePage(@RequestParam("query") String query, Model model) {
        List<Category> categories = categoryService.getAllCategoriesWithFilteredLinks(query);

        model.addAttribute("categories", categories);
        model.addAttribute("noResults", categories.isEmpty()); // Add a flag for empty results
        model.addAttribute("query", query);
        return "dashboard/category-search-result";
    }
}
