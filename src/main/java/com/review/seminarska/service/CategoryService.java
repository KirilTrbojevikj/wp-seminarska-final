package com.review.seminarska.service;

import com.review.seminarska.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> getAllCategories();

    public void addCategory(Category category);

    public void removeCategoryById(Long id);

    public Optional<Category> getCategoryById(Long id);
}
