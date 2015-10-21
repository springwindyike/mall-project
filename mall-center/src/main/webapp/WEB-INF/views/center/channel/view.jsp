<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8">
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
        type="text/css"/>
  <title>渠道详细信息</title>
</head>
<body>
<div class="pd-20">
  <table class="table">
    <tbody>
    <tr>
      <th class="text-r" width="80">供货商名字：</th>
      <td>${channelDTO.name}</td>
    </tr>
    <tr>
      <th class="text-r">应用ID：</th>
      <td>${channelDTO.appId}</td>
    </tr>
    <tr>
      <th class="text-r">应用秘钥：</th>
      <td>${channelDTO.appSecret}</td>
    </tr>
    <tr>
      <th class="text-r">地址：</th>
      <td>${channelDTO.country}&nbsp${channelDTO.province}&nbsp${channelDTO.city}&nbsp${channelDTO.district}&nbsp${channelDTO.detail}</td>
    </tr>
    <tr>
      <th class="text-r">联系电话：</th>
      <td>${channelDTO.phone}</td>
    </tr>
    <tr>
      <th class="text-r">联系人姓名：</th>
      <td>${channelDTO.linkName}</td>
    </tr>
    <tr>
      <th class="text-r">邮政编码：</th>
      <td>${channelDTO.code}</td>
    </tr>
    <tr>
      <th class="text-r">公司营业规模：</th>
      <td>${channelDTO.businessScale}</td>
    </tr>
    <tr>
      <th class="text-r">经营类别：</th>
      <td>${channelDTO.industry}</td>
    </tr>
    <tr>
      <th class="text-r">创建时间：</th>
      <td>${channelDTO.createTime}</td>
    </tr>
    <tr>
      <th class="text-r">更新时间：</th>
      <td>${channelDTO.updateTime}</td>
    </tr>
    <tr>
      <th class="text-r">创建者：</th>
      <td>${channelDTO.createBy}</td>
    </tr>
    </tbody>
  </table>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script>
</body>
</html>