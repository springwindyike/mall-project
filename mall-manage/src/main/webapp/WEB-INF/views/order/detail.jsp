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
  <meta name="viewport"
        content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link href="${pageContext.request.contextPath}/resources/shopnc/base.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/seller_center.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/perfect-scrollbar.min.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/skin_0.css" rel="stylesheet" type="text/css" />
  <title>订单详情</title>
</head>
<body>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>

<div class="page">
<table class="table tb-type2 order">
<tbody>
<tr class="space">
  <th colspan="2">订单详情</th>
</tr>
<tr>
  <th>订单信息</th>
</tr>
<tr>
  <td colspan="2"><ul>
    <li>
      <strong>订单号:</strong>${orderDetailDTO.orderId}
    </li>
    <li><strong>订单状态:</strong>${orderDetailDTO.stateValue}</li>
    <li><strong>订单总额:</strong><span class="red_common">￥${orderDetailDTO.productTotalPrice}</span>
      <!--//zmr>v80-->


    </li>
    <li><strong>运费:</strong>￥${orderDetailDTO.deliverFee}</li>
  </ul></td>
</tr>
<tr>
  <td><ul>
    <li><strong>买家：</strong>${orderDetailDTO.createBy}</li>
    <li><strong>店铺：</strong>${orderDetailDTO.channelName}</li>
    <li><strong>支付方式：</strong>${orderDetailDTO.paymentWay}</li>
    <li><strong>下单时间：</strong>${orderDetailDTO.createTime}</li>
  </ul></td>
</tr>
<tr>
  <th>收货人信息</th>
</tr>
<tr>
  <td><ul>
    <li><strong>收货人姓名：</strong>${orderDetailDTO.deliver.recipients}</li>
    <li><strong>电话号码：</strong>${orderDetailDTO.deliver.mobile}&nbsp;&nbsp;${orderDetailDTO.deliver.tel}</li>
    <li><strong>详细地址：</strong>${orderDetailDTO.deliver.province} ${orderDetailDTO.deliver.city} ${orderDetailDTO.deliver.district} ${orderDetailDTO.deliver.detail}</li>
  </ul></td>
</tr>
<tr>
  <th>商品信息</th>
</tr>
<tr>
<td><table class="table tb-type2 goods ">
<tbody>
<tr>
  <th></th>
  <th>商品信息</th>
  <th class="align-center">单价</th>
  <th class="align-center">实际支付额</th>
  <th class="align-center">数量</th>
  <th class="align-center">佣金比例</th>
  <th class="align-center">收取佣金</th>
</tr>
<c:choose>
  <c:when test="${orderDetailDTO.items != null && orderDetailDTO.items.size() != 0}">
    <c:forEach var="item" items="${orderDetailDTO.items}" >
      <tr>
      <td class="w60 picture"><div class="size-56x56"><span class="thumb size-56x56"><i></i><a href="" target="_blank"><img width="60px" alt="" src="${item.imageUrl}" /> </a></span></div></td>
      <td class="w50pre"><p><a href="" target="_blank">${item.productName}</a></p><p></p></td>
      <td class="w96 align-center"><span class="red_common">￥<c:out value="${item.productPrice}"/></span></td>
      <td class="w96 align-center"><span class="red_common">￥<c:out value="${item.productPrice * item.amount}"/></span></td>
      <td class="w96 align-center"><c:out value="${item.amount}"/></td>
      <td class="w96 align-center">0%</td>
      <td class="w96 align-center">0.00</td>
      </tr>
    </c:forEach>
    </c:when>
    <c:otherwise>
      没有数据
    </c:otherwise>
  </c:choose>
      </tbody>
      </table></td>
      </tr>


      </tbody>
      <tfoot>
      <tr class="tfoot">
      <td><a href="JavaScript:void(0);" class="btn" onclick="history.go(-1)"><span>返回</span></a></td>
      </tr>
      </tfoot>
      </table>
      </div>
      </body>
</html>
