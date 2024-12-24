package com.example.bupdashboard.service.impl;

import com.example.bupdashboard.entity.Category;
import com.example.bupdashboard.entity.Link;
import com.example.bupdashboard.repository.CategoryRepository;
import com.example.bupdashboard.repository.LinkRepository;
import com.example.bupdashboard.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Link saveLink(Link link, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        for (Category category : categories) {
            category.addLink(link);
        }
        categoryRepository.saveAll(categories);
        return linkRepository.save(link);
    }

    @Override
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }
}
