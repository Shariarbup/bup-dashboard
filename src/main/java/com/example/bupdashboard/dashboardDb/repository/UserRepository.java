package com.example.bupdashboard.dashboardDb.repository;

import com.example.bupdashboard.dashboardDb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByEmailContainingIgnoreCase(String email);
}
