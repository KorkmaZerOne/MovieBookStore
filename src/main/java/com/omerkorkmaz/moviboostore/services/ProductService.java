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
//        Product persistedProduct = getProductBySku(product.getSku());
//        if(persistedProduct!= null){
//            throw new MovibooException("Product SKU "+product.getSku()+" already exist");
//        }
        return productRepository.save(product);
    }

    public List<Product> searchProducts(String query) {
        return productRepository.search("%"+query+"%");
    }




}
