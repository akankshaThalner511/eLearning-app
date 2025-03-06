package com.expenceTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenceTracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String category);
}