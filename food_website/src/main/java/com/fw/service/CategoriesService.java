package com.fw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.Entity.Category;
import com.fw.repository.ICategoriesRepository;

@Service
public class CategoriesService 
{
	@Autowired
	private ICategoriesRepository repo;
	
	public void saveCategory(Category category)
	{
		repo.save(category);
	}
	
	public List<Category> getAllCategory()
	{
		return repo.findAll();
	}
	
	public String deleteCategory(int id)
	{
		Optional<Category> byId = repo.findById(id);
		if(byId.isPresent())
		{
			repo.deleteById(id);
			return "deleted";
		}
		return "no such category found";
	}
	
	public Category getCategory(int id)
	{
		Optional<Category> byId = repo.findById(id);
		if(byId.isPresent())
		{
			return byId.get();
		}
		throw new IllegalArgumentException("no such catergory found");
	}
}
