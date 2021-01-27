package com.omerkorkmaz.moviboostore.repositories;

import com.omerkorkmaz.moviboostore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Integer> {

    Category getByName(String name);

}
