package com.example.bupdashboard.dashboardDb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonManagedReference
    private List<Link> links = new ArrayList<>();

    public void addLink(Link link) {
        if (link != null && !this.links.contains(link)) {
            this.links.add(link); // Add to the links set
            link.getCategories().add(this); // Update the other side of the relationship
        }
    }

}
