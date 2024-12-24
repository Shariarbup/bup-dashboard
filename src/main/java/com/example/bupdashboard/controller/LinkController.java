package com.example.bupdashboard.controller;

import com.example.bupdashboard.entity.Link;
import com.example.bupdashboard.service.CategoryService;
import com.example.bupdashboard.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService linkService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/new")
    public String createLinkForm(Model model) {
        model.addAttribute("link", new Link());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "links/new";
    }

    @PostMapping("/new")
    public String saveLink(@ModelAttribute("link") Link link, @RequestParam List<Long> categoryIds) {

        linkService.saveLink(link, categoryIds);
        return "redirect:/links";
    }

    @GetMapping
    public String listLinks(Model model) {
        model.addAttribute("links", linkService.getAllLinks());
        return "links/list";
    }
}
