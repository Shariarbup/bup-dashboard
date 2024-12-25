package com.example.bupdashboard.controller;

import com.example.bupdashboard.dao.LinkDto;
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
//        linkService.saveLinkWithDto(link);
        return "redirect:/links";
    }

    @GetMapping
    public String listLinks(Model model) {
        model.addAttribute("links", linkService.getAllLinks());
        return "links/list";
    }

    @GetMapping("/{id}/edit")
    public String showEditLinkForm(@PathVariable Long id, Model model) {
        Link link = linkService.findCategoryById(id);
        model.addAttribute("link", link);
        model.addAttribute("categories", categoryService.getAllCategories()); // For category dropdown
        return "links/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateLink(@PathVariable Long id, @ModelAttribute("link") Link updatedLink, @RequestParam("categoryIds") List<Long> categoryIds) {
        linkService.updateLink(id, updatedLink, categoryIds);
        return "redirect:/links"; // Redirect to the link list page
    }


    @GetMapping("/search")
    public String searchLinks(@RequestParam("query") String query, Model model) {
        // Fetch categories and filter links based on the search query
        List<Link> links = linkService.fetchLinkByQuery(query);
        model.addAttribute("links", links);
        model.addAttribute("noResults", links.isEmpty()); // Add a flag for empty results
        return "links/list :: #links-container";
    }
}
