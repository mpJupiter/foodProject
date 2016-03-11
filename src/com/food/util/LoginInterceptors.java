package com.food.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.food.model.Customer;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
 
public class LoginInterceptors extends AbstractInterceptor
{
	private static final long serialVersionUID=1L;
	
	private String sessionName;
	public String getSessionName()
	{
		return sessionName;
	}
	public void setSessionName(String sessionName)
	{
		this.sessionName=sessionName;
	}

	private String excludeName;
	public String getExcludeName() {
		return excludeName;
	}
	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
	}
	
	private List <String> list;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
	public List<String> strlist(String str)
	{
		String []s=str.split(",");
		List  <String> list =new ArrayList<String>();
		for(String ss:s)
		{
			list.add(ss.trim());
		}
		return list;
	}
	 @Override
	public void init()
	{
		list=strlist(excludeName);
	}
	 
	 @Override
	 public  String intercept(ActionInvocation invocation) throws Exception
	{
		System.out.println("-----进入拦截器---------");
		String actionName=invocation.getProxy().getActionName();
		Map<String,Object> session=invocation.getInvocationContext().getSession();
		
		if(list.contains(actionName))
		{
			System.out.println(actionName+"没有被拦截");
			return invocation.invoke();
		}
		else
		{
			System.out.println(actionName+"被拦截了");
		    Customer customer=(Customer) session.get(sessionName);
			if(customer==null)
			{
				HttpServletRequest req=ServletActionContext.getRequest();
				String path =req.getRequestURI().replaceAll("/foodProject", "");
				System.out.println("path:"+path);
				session.put("prepage",path);
				return "login";
			}
			else
			{
				return invocation.invoke();
			}
		}
	}
	
	
}
