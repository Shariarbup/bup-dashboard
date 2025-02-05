package com.example.bupdashboard.dashboardDb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String url;

    @ManyToMany(mappedBy="links", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Category> categories =new HashSet<>();

    public void addCategory(Category category) {
        if (category != null && !this.categories.contains(category)) {
            this.categories.add(category); // Add to the categories set
            category.getLinks().add(this); // Update the other side of the relationship
        }
    }
}
