package com.fw.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="PRODUCTS_TAB")
public class Product implements Serializable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PRODUCT_ID")
	private Long productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="PRODUCT_DES")
	private String description;
	
	@Column(name="PRODUCT_PRICE")
	private Double price;
	
	@Column(name="PRODUCT_IMG")
	private String imageName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID",referencedColumnName = "CID")
	private Category category;

}
