<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/9/1
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />

<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>物流信息</title>
		<meta name="keywords" content="享买开放平台">
		<meta name="description" content="享买开放平台">
</head>

<body>
<div class="pd-20">
  <form action="${pageContext.request.contextPath}/register.dhtml" method="post" class="form form-horizontal" id="form-member-add">
    <div class="row cl">
      <label class="form-label col-3">以下是物流信息：</label>
				
    </div>

  </form>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript">
function getLogistics(){
	$.ajax({
		//提交数据的类型 POST GET
		type:"POST",
		//提交的网址
		url:"${pageContext.request.contextPath}/find/${order}/${id}.dhtml",
		 //提交的数据
		data:{},
		 //返回数据的格式
		datatype: "json",
		 //在请求之前调用的函数
		//beforeSend:function(){$("#msg").html("logining");},
		 //成功返回之后调用的函数            
		success:function(data){
			//$("#msg").html(decodeURI(data));           
		 }   ,
		 //调用执行后调用的函数
		complete: function(XMLHttpRequest, textStatus){
			alert(XMLHttpRequest.responseText);
			alert(textStatus);
			//HideLoading();
		 },
		 //调用出错执行的函数
		error: function(){
			//请求出错处理
		 }        
	});
}
</script>
</body>
</html>