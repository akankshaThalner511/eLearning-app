package com.expenceTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenceTracker.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String role);
}