package com.example.bupdashboard.dashboardDb.repository;

import com.example.bupdashboard.dashboardDb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
