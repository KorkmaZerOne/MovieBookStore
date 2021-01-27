package com.omerkorkmaz.moviboostore.repositories;

import com.omerkorkmaz.moviboostore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findBySku(String sku);

    @Query(value = "select p from Product p where p.title like ?1 or p.sku like ?1")
    List<Product> search(String query);
}
