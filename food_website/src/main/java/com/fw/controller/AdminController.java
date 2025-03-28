package com.fw.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fw.Entity.Category;
import com.fw.Entity.Product;
import com.fw.model.ProductData;
import com.fw.service.CategoriesService;
import com.fw.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private ProductService productService;
	
	@Value("${product.images.location}")
	private String storeLocation;
	
	
	
	@GetMapping("/home")
	public String adminLogin()
	{
		return "adminHome";
	}
	
	
	@GetMapping("/categories")
	public String getCategories(Map<String, Object> map)
	{
		map.put("categories", categoriesService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/categories/add")
	public String addCategories(@ModelAttribute("category") Category category)
	{
		return "categoriesAdd";
	}
	
	@PostMapping("/categories/add")
	public String savecategorie(@ModelAttribute("category") Category category) {
		categoriesService.saveCategory(category);
	    return "redirect:/admin/categories";
	}
	
	@GetMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id)
	{
		categoriesService.deleteCategory(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/categories/update/{id}")
	public String updtaeCategory(@PathVariable int id,Map<String, Object> map)
	{
		 Category category = categoriesService.getCategory(id);
		 System.out.println(category);
		map.put("category", category);
		return "categoriesAdd";
	}
	
	
	@GetMapping("/products")
	public String ProductsPage(Map<String,Object> map)
	{
		map.put("products", productService.getAllProducts());
		return "products";
	}
	
	@GetMapping("/products/add")
	public String loadAddProductPage(@ModelAttribute("productData") ProductData productData,Map<String,Object> map)
	{
		map.put("categories",categoriesService.getAllCategory());
		return "productsAdd";
	}
	
	
	@PostMapping("/products/add")
	public String addProduct(@ModelAttribute("productData")ProductData productData,
			@RequestParam("productImage")MultipartFile file
			) throws IllegalStateException, IOException  
	{
		Product product=new Product();
		product.setProductId(productData.getProductId());
		product.setProductName(productData.getProductName());
		product.setPrice(productData.getPrice());
		product.setDescription(productData.getDescription());
		product.setCategory(categoriesService.getCategory(productData.getCategoryId()));
		String filepath=storeLocation+file.getOriginalFilename();
		file.transferTo(new File(filepath)); //copying the img to destination 
		product.setImageName("/productImages/"+file.getOriginalFilename());
		productService.saveProduct(product);
		return "redirect:/admin/products";	
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable long id)
	{
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/product/update/{id}")
	public String updateProductPageLoad(@PathVariable long id, Map<String, Object> map)
	{
		ProductData productData = new ProductData();
		Product product = productService.getProduct(id);
		
		productData.setProductId(product.getProductId());
		productData.setProductName(product.getProductName());
		productData.setDescription(product.getDescription());
		productData.setPrice(product.getPrice());
		productData.setCategoryId(product.getCategory().getCid());
		map.put("productData",productData );
		map.put("categories",categoriesService.getAllCategory());
		return "productUpdate";
	}
	
	@PostMapping("/products/update")
	public String updateProduct(@ModelAttribute ProductData productData,
								@RequestParam("productImage") MultipartFile file,
								Map<String, Object> map) throws IllegalStateException, IOException
	{
		Product product=new Product();
		product.setProductId(productData.getProductId());
		product.setProductName(productData.getProductName());
		product.setPrice(productData.getPrice());
		product.setDescription(productData.getDescription());
		product.setCategory(categoriesService.getCategory(productData.getCategoryId()));
		if (file != null && !file.isEmpty()) 
		{
			String filepath=storeLocation+file.getOriginalFilename();
			System.out.println("/productImages/"+file.getOriginalFilename());
			System.out.println(filepath);
			file.transferTo(new File(filepath));
			product.setImageName("/productImages/"+file.getOriginalFilename());
		}else {
			String imageName = productService.getProduct(productData.getProductId()).getImageName();
			product.setImageName(imageName);
		}
		productService.saveProduct(product);
		return "redirect:/admin/products";	
	}
	
	
	
	
}
