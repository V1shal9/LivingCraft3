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

import com.craft.livingcraft.model.CartItem;
import com.craft.livingcraft.model.Product;
import com.craft.livingcraft.services.CartItemService;
import com.craft.livingcraft.services.ProductService;
import com.craft.livingcraft.services.UserDetailsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CartItemController 
{
	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("buynow-{productId}")
	public String buyNow(@PathVariable("productId")int productId,@ModelAttribute("cartItem")CartItem cartItem,Model model,@RequestParam("userId")int userId,Product product, HttpSession httpSession )
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userName=auth.getName();
		userId=userDetailsService.getUserByName(userName).getUserId();
		cartItem.setCartId(userId);
		cartItem.setUserId(userId);
		cartItem.setProductId(productId);
		cartItem.setFlag(false);
		cartItem.setProductQuantity(1);
		
		String productName=productService.getProductById(productId).getProductName();
		cartItem.setProductName(productName);
		
		float productPrice=productService.getProductById(productId).getProductPrice();
		cartItem.setProductPrice(productPrice);
		
		int productDiscount=productService.getProductById(productId).getProductDiscount();
		cartItem.setProductDiscount(productDiscount);
		 
		cartItem.setFlag(false);
		cartItem.setProductQuantity(1);
	
		cartItemService.addCartItem(cartItem);
		productService.updateProductQuantity(productId);

		httpSession.setAttribute("cartItemId", cartItem.getCartItemId());
		int cartItemId=(Integer)httpSession.getAttribute("cartItemId");
	
	    return "redirect:/cartlist-"+cartItemId;
	}
	
	@RequestMapping("cartlist-{cartItemId}")
	public String cartList(HttpSession httpSession,Model model)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userName=auth.getName();
     	int userId=userDetailsService.getUserByName(userName).getUserId();
		
	    httpSession.setAttribute("userId", userId);
				
		int cartItemId=(Integer)httpSession.getAttribute("cartItemId");

		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String prd=gson.toJson(cartItemService.getCartItemById(cartItemId));
		model.addAttribute("product", prd);
		
		
		return "cartlist";
	}
	
	@RequestMapping("checkout")
	public String checkOut(@RequestParam("userId")int userId,HttpSession httpSession)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userName=auth.getName();
     	userId=userDetailsService.getUserByName(userName).getUserId();
     	
     	httpSession.setAttribute("userId", userId);
     	
		int cartItemId=(Integer)httpSession.getAttribute("cartItemId");
     	
		httpSession.setAttribute("cartItemId", cartItemId);
		return "redirect:/cart?userId="+userId;
	}
	
	@RequestMapping("addtocart-{productId}")
	public String addToCart(@PathVariable("productId")int productId,@ModelAttribute("cartItem")CartItem cartItem,Model model,@RequestParam("userId")int userId,Product product, HttpSession httpSession )
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userName=auth.getName();
		userId=userDetailsService.getUserByName(userName).getUserId();
		cartItem.setCartId(userId);
		cartItem.setUserId(userId);
		cartItem.setProductId(productId);
		cartItem.setFlag(false);
		cartItem.setProductQuantity(1);
		
		String productName=productService.getProductById(productId).getProductName();
		cartItem.setProductName(productName);
		
		float productPrice=productService.getProductById(productId).getProductPrice();
		cartItem.setProductPrice(productPrice);
		
		int productDiscount=productService.getProductById(productId).getProductDiscount();
		cartItem.setProductDiscount(productDiscount);
		 
		cartItem.setFlag(false);
		cartItem.setProductQuantity(1);
	
		cartItemService.addCartItem(cartItem);
		productService.updateProductQuantity(productId);

		httpSession.setAttribute("cartItemId", cartItem.getCartItemId());
		int cartItemId=(Integer)httpSession.getAttribute("cartItemId");
	
	    return "redirect:/addcart-"+cartItemId;
	}
	
	@RequestMapping("addcart-{cartItemId}")
	public String addToCartList(HttpSession httpSession,Model model)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String userName=auth.getName();
     	int userId=userDetailsService.getUserByName(userName).getUserId();
		
	    httpSession.setAttribute("userId", userId);
				

		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String prd=gson.toJson(cartItemService.getCartItemList());
		model.addAttribute("product", prd);
		
		
		return "redirect:/";
	}
}
