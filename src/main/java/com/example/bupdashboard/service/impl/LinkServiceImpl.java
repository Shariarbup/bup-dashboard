package com.example.bupdashboard.service.impl;

import com.example.bupdashboard.dao.LinkDto;
import com.example.bupdashboard.entity.Category;
import com.example.bupdashboard.entity.Link;
import com.example.bupdashboard.repository.CategoryRepository;
import com.example.bupdashboard.repository.LinkRepository;
import com.example.bupdashboard.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Link saveLink(Link link, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
//        link.setCategories(categories);
        for (Category category : categories) {
            link.addCategory(category);
        }

        // Save the entities
//        categoryRepository.saveAll(categories);
        return linkRepository.save(link);
    }

    @Override
    public void saveLinkWithDto(LinkDto linkDto) {
        Link link = new Link();
        link.setName(linkDto.getName());
        link.setUrl(linkDto.getUrl());
        link.setCategories(linkDto.getCategories());
        System.out.println("link c"+ linkDto.getCategories());
        linkRepository.save(link);
    }

    @Override
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }
}
