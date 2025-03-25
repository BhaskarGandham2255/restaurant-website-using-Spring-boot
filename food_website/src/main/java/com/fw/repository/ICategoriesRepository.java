package com.fw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fw.Entity.Category;

public interface ICategoriesRepository extends JpaRepository<Category, Integer>{

}
