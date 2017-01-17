package com.craft.livingcraft.dao;

import java.util.List;

import com.craft.livingcraft.model.WishList;

public interface WishListDAO 
{
	public void addWishList(WishList wishList);
	public List<WishList> getWishList();

}
