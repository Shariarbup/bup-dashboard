package com.example.bupdashboard.dao;

import com.example.bupdashboard.dashboardDb.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkDto {
    private Long id;

    private String name;

    private String url;

    private List<Category> categories =new ArrayList<>();
}
