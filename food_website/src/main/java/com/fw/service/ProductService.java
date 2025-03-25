package com.fw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.Entity.Product;
import com.fw.repository.IProductReposotiry;

@Service
public class ProductService 
{
	@Autowired
	private IProductReposotiry repo;
	
	public List<Product> getAllProducts()
	{
		return repo.findAll();
	}
	
	public Product getProduct(long id)
	{
		Optional<Product> byId = repo.findById(id);
		
		if(byId.isPresent())
			return byId.get();
		else
			throw new RuntimeException("product not found");
	}
	
	public Product saveProduct(Product product)
	{
		return repo.save(product);
	}
	
	public void deleteProductById(long id)
	{
Optional<Product> byId = repo.findById(id);
		
		if(byId.isPresent())
			repo.deleteById(id);
		else
			throw new RuntimeException("product not found");
	}
	
	public List<Product> getAllProductsByCategoryId(int id)
	{
		return repo.findByCategory_Cid(id);
	}
}
