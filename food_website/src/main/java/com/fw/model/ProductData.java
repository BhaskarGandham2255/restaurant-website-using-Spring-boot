package com.fw.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductData 
{
    private Long productId;
	
	private String productName;
	
	private String description;
	
	private Double price;
	
	private MultipartFile productImage;
	
//	private String imageName;
	
	private Integer categoryId;
}
