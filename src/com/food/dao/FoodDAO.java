package com.food.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.model.Food;
import com.food.model.Customer;;

@Service@Transactional
public class FoodDAO 
{
	@Resource SessionFactory factory;
	
	//增
	public void AddFood(Food food)throws Exception
	{
		Session s=factory.getCurrentSession();
		s.save(food);
	}
	//删
	public void DeleteFood(Integer foodid)throws Exception
	{
		Session s =factory.getCurrentSession();
		Object food =s.load(Food.class,foodid);
		s.delete(food);
	}
	//改
	public void UpdateFood(Food food)throws Exception
	{
		Session s =factory.getCurrentSession();
		s.update(food);
	}
	//查:所有Food信息
	public ArrayList<Food> QueryAllFood()
	{
		Session s =factory.getCurrentSession();
		String hql ="From Food";
		Query q=s.createQuery(hql);
		List foodList=q.list();
		return (ArrayList<Food>) foodList;
	}
	//查：根据主键查询
     public Food GetFoodById(Integer foodid) 
    {
        Session s = factory.getCurrentSession();
        Food food = (Food)s.get(Food.class, foodid);   //强制转换成food类型
        return food;
    }
     //查：根据查询条件查询
     public ArrayList<Food> QueryFoodInfo(String foodname) 
     { 
     	Session s = factory.getCurrentSession();
     	String hql = "From Food food where 1=1";
     	if(!foodname.equals("")) hql = hql + " and food.foodname like '%" + foodname + "%'";
     	Query q = s.createQuery(hql);
     	List foodList = q.list();
     	return (ArrayList<Food>) foodList;
     }
}
