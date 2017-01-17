package com.craft.livingcraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.craft.livingcraft.model.Category;
import com.craft.livingcraft.services.CategoryService;
import com.craft.livingcraft.services.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class MainController 
{
 
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public String hello(Model model) {
		
		model.addAttribute("category", new Category());
		model.addAttribute("categoryList2", categoryService.getCategoryList());
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String productList=gson.toJson(productService.getProductList());
		model.addAttribute("productList", productList);
		
		return "index";
	}
}
