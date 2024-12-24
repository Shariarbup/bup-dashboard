package com.example.bupdashboard.service;

import com.example.bupdashboard.dao.LinkDto;
import com.example.bupdashboard.entity.Link;

import java.util.List;

public interface LinkService {
    Link saveLink(Link link, List<Long> categoryIds);

    void saveLinkWithDto(LinkDto linkDto);

    List<Link> getAllLinks();

    Link findCategoryById(Long id);

    void updateLink(Long id, Link updatedLink, List<Long> categoryIds);
}
