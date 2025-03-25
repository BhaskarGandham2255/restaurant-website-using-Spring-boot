package com.fw.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fw.Entity.Category;
import com.fw.repository.ICategoriesRepository;
import com.fw.repository.IProductReposotiry;
import com.fw.service.CategoriesService;
import com.fw.service.ProductService;

@Controller
public class HomePageController 
{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	
	@GetMapping({"/","/home"})
	public String loadHome()
	{
		return "index";
	}
	
	@GetMapping("/shop")
	public String shopPage(Map<String, Object> map)
	{
		map.put("categories",categoriesService.getAllCategory());
		map.put("products", productService.getAllProducts());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String loadProductsByCategory(@PathVariable int id,Map<String, Object> map)
	{
		map.put("categories", categoriesService.getAllCategory());
		map.put("products",productService.getAllProductsByCategoryId(id));
		return "shop";
	}
}
