package com.craft.livingcraft.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.craft.livingcraft.daoimpl.WishListDAIOImpl;
import com.craft.livingcraft.model.WishList;

@Transactional
@Service
public class WishListService 
{
	@Autowired
	WishListDAIOImpl wishListDAIOImpl;
	
	public void addWishList(WishList wishList) 
	{
		wishListDAIOImpl.addWishList(wishList);
	}

	public List<WishList> getWishList() 
	{
		return wishListDAIOImpl.getWishList();
	}
}
