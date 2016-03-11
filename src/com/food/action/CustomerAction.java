package com.food.action;


import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.food.dao.CustomerDAO;
import com.food.model.Customer;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")

public class CustomerAction extends ActionSupport implements SessionAware//必须写 implements SessionAware
{
	private static final long serialVersionUID = 1L;
	
	@Resource CustomerDAO customerDao;

	private Map<String ,Object>session;
	public Map<String, Object> getSession() 
	{
		return session;
	}
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

	private String prePage;
	public String getPrePage() 
	{
		return prePage;
	}
	public void setPrePage(String prePage)
	{
		this.prePage = prePage;
	}

	
	private Customer customer;
	public Customer getCustomer()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer=customer;
	}
	
    private String errMessage;
	public String getErrMessage()
	{
		return errMessage;
	}
	
	public void setErrMessage(String errMessage)
	{
		this.errMessage=errMessage;
	}
	
	public String register()throws Exception
	{
		customerDao.AddCustomer(customer);
		session.put("customer",customer);
		return "show_view";
	}
	
	//验证用户登录
		
	public String login()
	{
		ArrayList<Customer>listcustomer=customerDao.QueryCustomerInfo(customer.getName()) ;
		
		if(listcustomer.size()==0)
		{
			this.errMessage="账号不存在";
			System.out.println(this.errMessage);
			return INPUT;
		}
		else
		{
            Customer db_customer= listcustomer.get(0);
			
			if(!db_customer.getPassword().equals(customer.getPassword()))
			{
				this.errMessage="密码不正确";
				System.out.println(this.errMessage);
				return "input";
			}
			else
			{
				session.put("customer", db_customer);
				
				return "show_view"; 
			}
		}	
	}
}
