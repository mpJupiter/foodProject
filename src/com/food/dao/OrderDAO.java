package com.food.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.model.Order;
import com.food.model.Food;
import com.food.model.Customer;

@Service@Transactional

public class OrderDAO 
{
	@Resource SessionFactory factory;
 
	//增
	public void AddOrder(Order order)throws Exception
	{
		Session s=factory.getCurrentSession();
		s.save(order);
	}
	//删
	public void DeleteOrder(Integer orderid)throws Exception
	{
		Session s =factory.getCurrentSession();
		Object order =s.load(Order.class,orderid);
		s.delete(order);
	}
	//改
	public void UpdateOrder(Order order)throws Exception
	{
		Session s =factory.getCurrentSession();
		s.update(order);
	}
	//查:所有Order信息
	public ArrayList<Order> QueryAllOrder()
	{
		Session s =factory.getCurrentSession();
		String hql ="From Order";
		Query q=s.createQuery(hql);
		List orderList=q.list();
		return (ArrayList<Order>) orderList;
	}
	//查：根据主键查询
     public Order GetOrderById(int orderid) 
    {
        Session s = factory.getCurrentSession();
        Order order = (Order)s.get(Order.class,orderid);
        return order;
    }
     //查：根据查询条件查询
     public ArrayList<Order> QueryOrderInfo(Customer customer,Food food) 
     { 
     	Session s = factory.getCurrentSession();
     	String hql = "From Order order where 1=1";
     	if(null!=customer&&customer.getCustomerid()!=0)
     		hql = hql + " and order.customer.customerid like '%" +  customer.getCustomerid() + "%'";
 		if(null!=food&&null!=food.getFoodname())
     		hql=hql+"and order.food.foodname like '%"+food.getFoodname()+"%'";
     	
     	Query q = s.createQuery(hql);
     	List  orderList = q.list();
     	return (ArrayList<Order>) orderList;
     }
}
	