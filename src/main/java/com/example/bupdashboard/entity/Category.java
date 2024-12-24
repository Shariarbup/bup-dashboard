package com.example.bupdashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="categories_links",
            joinColumns={@JoinColumn(name="CATEGORY_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="LINK_ID", referencedColumnName="id")})
    private List<Link> links = new ArrayList<>();

    public void addLink(Link link) {
        this.links.add(link);
        link.getCategories().add(this);
    }
}
