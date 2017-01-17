package com.craft.livingcraft.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.craft.livingcraft.dao.WishListDAO;
import com.craft.livingcraft.model.Category;
import com.craft.livingcraft.model.WishList;

@Repository
public class WishListDAIOImpl implements WishListDAO
{

	@Autowired
	SessionFactory sessionFactory;
	
	public void addWishList(WishList wishList) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(wishList);
		
	}

	public List<WishList> getWishList() 
	{
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<WishList> wishList = session.createQuery("from WishList").getResultList();
		return wishList;
		
	}
	

}
