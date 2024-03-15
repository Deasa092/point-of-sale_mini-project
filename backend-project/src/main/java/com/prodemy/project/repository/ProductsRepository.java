package com.prodemy.project.repository;

import com.prodemy.project.entity.Category;
import com.prodemy.project.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository <Products, Integer>  {
}
