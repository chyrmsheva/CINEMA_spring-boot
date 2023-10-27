package com.example.cinema_springboot.repository;

import com.example.cinema_springboot.model.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
