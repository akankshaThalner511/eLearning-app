package com.expenceTracker.service;

import com.expenceTracker.entity.Category;

public interface CategoryService {
    Category findCategoryByName(String name);
    Category findCategoryById(int id);
}