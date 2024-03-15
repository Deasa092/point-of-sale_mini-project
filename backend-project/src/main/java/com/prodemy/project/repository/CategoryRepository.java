package com.prodemy.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prodemy.project.entity.Category;

public interface CategoryRepository extends JpaRepository <Category, Integer>{

}