package com.craft.livingcraft.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.craft.livingcraft.model.Product;
import com.craft.livingcraft.model.WishList;
import com.craft.livingcraft.services.ProductService;
import com.craft.livingcraft.services.UserDetailsService;
import com.craft.livingcraft.services.WishListService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class WishListController 
{
	@Autowired
	WishListService wishListService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("wishlist-{productId}")
	public String buyNow(@PathVariable("productId")int productId,@ModelAttribute("wishList")WishList wishList,Model model,@RequestParam("userId")int userId,Product product, HttpSession httpSession )
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userName=auth.getName();
		userId=userDetailsService.getUserByName(userName).getUserId();
		wishList.setCartId(userId);
		wishList.setUserId(userId);
		wishList.setProductId(productId);
		wishList.setFlag(false);
		
		String productName=productService.getProductById(productId).getProductName();
		wishList.setProductName(productName);
		
		float productPrice=productService.getProductById(productId).getProductPrice();
		wishList.setProductPrice(productPrice);
		
		int productDiscount=productService.getProductById(productId).getProductDiscount();
		wishList.setProductDiscount(productDiscount);
		 
	
		wishListService.addWishList(wishList);

	
	    return "redirect:/wishlist";
	}

	@RequestMapping("wishlist")
	public String cartList(HttpSession httpSession,Model model)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userName=auth.getName();
     	int userId=userDetailsService.getUserByName(userName).getUserId();
		
	    httpSession.setAttribute("userId", userId);
				

		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String wishList=gson.toJson(wishListService.getWishList());
		model.addAttribute("wishlist", wishList);
		
		
		return "wishlist";
	}

}
