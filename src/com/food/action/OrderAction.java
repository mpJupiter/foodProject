package com.food.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.food.dao.CustomerDAO;
import com.food.dao.FoodDAO;
import com.food.dao.OrderDAO;
import com.food.model.Customer;
import com.food.model.Food;
import com.food.model.Order;
import com.opensymphony.xwork2.ActionSupport;


@Controller @Scope("prototype")

public class OrderAction extends ActionSupport
{
	@Resource OrderDAO  orderDao;
	@Resource CustomerDAO  customerDao;
	@Resource FoodDAO  foodDao;

	
	private Order order;
	
	public Order getOrder()
	{
		return order;
	}
	public void setOrder(Order order)
	{
		this.order=order;
	}
	
	private Customer customer;
	public Customer getCustoemr()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer=customer;
	}
	
	private Food food;
	public Food getFood()
	{
		return food;
	}
	public void setFood(Food food)
	{
		this.food=food;
	}
	
	private List<Order> orderList;
	public List<Order> getOrderList()
	{
		return orderList;
	}
	public void setOrderList(List<Order> orderList)
	{
		this.orderList=orderList;
	}
	
	

	public String addOrder() throws Exception
	{
		customer=customerDao.QueryCustomerInfo(customer.getName()).get(0);
		Order ord=new Order();
		ord.setCustomer(customer);
		ord.setFood(food);
		ord.setFoodnum(1);
		ord.setTotal(foodDao.GetFoodById(food.getFoodid()).getUnitprice()*1);
		orderDao.AddOrder(ord);
		return "order_message";
	}
	
	/*显示Order的修改项*/
    public String showEdit() throws Exception 
    {
    	order = orderDao.GetOrderById(order.getOrderid());
        return "edit_view";
    }
	
	public String showOrder()
	{
		System.out.println(customer.getName());
		Customer cus=customerDao.QueryCustomerInfo(customer.getName()).get(0);
		orderList=orderDao.QueryOrderInfo(cus,null);
		return "show_view";
	}
	
	public String showDetail()
	{
		System.out.println(order.getOrderid());
		order=orderDao.GetOrderById(order.getOrderid());
		return "detail_view";
	}
	
	/*编辑Order*/
    public String editOrder() throws Exception 
    {
    	orderDao.UpdateOrder(order);
        return "edit_message";
    }
    
    /*删除Order*/
    public String deleteOrder() throws Exception 
    {
    	orderDao.DeleteOrder(food.getFoodid());
        return "delete_message";
    }
	
	public String queryOrders()throws Exception
	{
		orderList = orderDao.QueryOrderInfo(customer, food);
		return "show_view";
	}
}
