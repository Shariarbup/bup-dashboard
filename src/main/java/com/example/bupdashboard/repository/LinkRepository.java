package com.example.bupdashboard.repository;

import com.example.bupdashboard.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByNameContainingIgnoreCase(String name);
}
