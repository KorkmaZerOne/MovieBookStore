package com.omerkorkmaz.moviboostore.services;

import com.omerkorkmaz.moviboostore.exception.MovibooException;
import com.omerkorkmaz.moviboostore.model.Category;
import com.omerkorkmaz.moviboostore.model.Product;
import com.omerkorkmaz.moviboostore.repositories.CategoryRepository;
import com.omerkorkmaz.moviboostore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    CategoryRepository categoryRepository;


    public List<Product> getAllProducts(){
        List<Product> list = new ArrayList<>();
        productRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    public Product getProductById(Integer id) {
        return productRepository.getOne(id);
    }

    public Product createProduct(Product product) {
        Product persistedProduct = getProductBySku(product.getSku());
        if(persistedProduct!= null){
            throw new MovibooException("Product SKU "+product.getSku()+" already exist");
        }
        return productRepository.save(product);
    }

    public List<Product> searchProducts(String query) {
        return productRepository.search("%"+query+"%");
    }

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.getByName(name);
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.getOne(id);
    }

    public Category createCategory(Category category) {
        Category persistedCategory = getCategoryByName(category.getName());
        if( persistedCategory != null){
            throw new MovibooException("Category "+category.getName()+" already exist");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        Category persistedCategory = getCategoryById(category.getCategoryId());
        if(persistedCategory == null){
            throw new MovibooException("Category "+category.getCategoryId()+" doesn't exist");
        }
        persistedCategory.setDescription(category.getDescription());
        persistedCategory.setDisplayOrder(category.getDisplayOrder());
        persistedCategory.setDisabled(category.isDisabled());
        return categoryRepository.save(persistedCategory);
    }


}
