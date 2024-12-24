package com.example.bupdashboard.controller;

import com.example.bupdashboard.entity.Category;
import com.example.bupdashboard.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
