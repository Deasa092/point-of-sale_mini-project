package com.prodemy.project.repository;

import com.prodemy.project.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository <Products, Integer>{
    List<Products> findByCategoryId(Integer categoryId);
    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY p.price ASC")
    List<Products> findByCategoryIdAndTitleAndSortByPriceAscending(Integer categoryId, String title);

    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY p.price DESC")
    List<Products> findByCategoryIdAndTitleAndSortByPriceDescending(Integer categoryId, String title);

    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY p.title ASC")
    List<Products> findByCategoryIdAndTitleAndSortByNameAscending(Integer categoryId, String title);

    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY p.title DESC")
    List<Products> findByCategoryIdAndTitleAndSortByNameDescending(Integer categoryId, String title);
    @Query("SELECT p FROM Products p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Products> findByTitle(String title);
    @Query("SELECT p FROM Products p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))" +
            "ORDER BY CASE WHEN :sortBy = 'price' THEN p.price END ASC")
    List<Products> findByTitleAndSortAscending(String title, String sortBy);

    @Query("SELECT p FROM Products p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))" +
            "ORDER BY CASE WHEN :sortBy = 'price' THEN p.price END DESC")
    List<Products> findByTitleAndSortDescending(String title, String sortBy);
}
