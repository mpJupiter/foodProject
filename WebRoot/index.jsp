<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>直接跳转到显示所有Food的页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
	
  </head>
  
  <body>
    <s:form action="food/food_AddFood" method="post">
	    <s:textfield name="food.foodname" label ="食品名称" ></s:textfield>
	    <s:textfield name="food.unitprice"  label = "单价"></s:textfield>
	    <s:submit value="保存"></s:submit>
    <br>
    </s:form>
    
    <s:form action="customer/customer_AddCustomer" method="post">
        <s:textfield name="customer.name" label ="顾客姓名" ></s:textfield>
        <s:textfield name="customer.address"  label = "顾客地址"></s:textfield>
        <s:submit value="保存"></s:submit>
    <br>
    </s:form>
    
    <s:form action="order/order_AddOrder" method="post">
        <s:textfield name="order.food" label ="食物" ></s:textfield>
        <s:textfield name="order.foodnum"  label = "食物数量"></s:textfield>
        <s:textfield name="order.customer" label ="顾客" ></s:textfield>
        <s:textfield name="order.total"  label = "总价"></s:textfield>
        <s:submit value="保存"></s:submit>
    <br>
    </s:form>
    
    
  </body>
</html>
