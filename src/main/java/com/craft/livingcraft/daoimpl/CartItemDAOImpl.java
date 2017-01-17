package com.craft.livingcraft.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.craft.livingcraft.dao.CartItemDAO;
import com.craft.livingcraft.model.CartItem;
import com.craft.livingcraft.model.ViewProduct;

@Repository
public class CartItemDAOImpl implements CartItemDAO
{
	@Autowired
	SessionFactory sessionFactory;

	public void addCartItem(CartItem cartItem) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(cartItem);
		
	}
	public CartItem getCartItemById(int cartItemId)
	{
		Session session=sessionFactory.getCurrentSession();
		String hql="from CartItem where cartItemId="+cartItemId;
		@SuppressWarnings("unchecked")
		List<CartItem> plist=session.createQuery(hql).getResultList();
		return plist.get(0);
	}

	
	public void updateFlag(int cartItemId) 
	{
		String hql="update CartItem set flag=true where cartItemId="+cartItemId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public CartItem getCartItemList()
	{
		Session session=sessionFactory.getCurrentSession();
		String hql="from CartItem";
		@SuppressWarnings("unchecked")
		List<CartItem> plist=session.createQuery(hql).getResultList();
		return plist.get(0);
	}
}
