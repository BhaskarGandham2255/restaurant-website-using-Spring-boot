package com.fw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fw.Entity.Product;

public interface IProductReposotiry extends JpaRepository<Product, Long>{

	List<Product> findByCategory_Cid(int cid);
}
