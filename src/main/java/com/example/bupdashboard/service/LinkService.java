package com.example.bupdashboard.service;

import com.example.bupdashboard.entity.Link;

import java.util.List;

public interface LinkService {
    Link saveLink(Link link, List<Long> categoryIds);

    List<Link> getAllLinks();
}
