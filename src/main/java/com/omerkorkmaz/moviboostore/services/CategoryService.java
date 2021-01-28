package com.omerkorkmaz.moviboostore.services;

import com.omerkorkmaz.moviboostore.exception.MovibooException;
import com.omerkorkmaz.moviboostore.model.Category;
import com.omerkorkmaz.moviboostore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.getByName(name);
    }

    public Category getCategoryById(int id) {
        return categoryRepository.getOne(id);
    }

    public Category createCategory(Category category) {
//        Category persistedCategory = getCategoryByName(category.getName());
//        if( persistedCategory != null){
//            throw new MovibooException("Category "+category.getName()+" already exist");
//        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
//        Category persistedCategory = getCategoryByName(category.getName());
//        if(persistedCategory == null){
//            throw new MovibooException("Category "+category.getName()+" doesn't exist");
//        }
//        persistedCategory.setDescription(category.getDescription());
//        persistedCategory.setDisplayOrder(category.getDisplayOrder());
//        persistedCategory.setDisabled(category.isDisabled());
        return categoryRepository.save(category);
    }
}
