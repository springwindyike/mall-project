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
  <link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
  <title>订单详情</title>
</head>
<body>
  <head>
  <div id="append_parent"></div>
  <div id="ajaxwaitid"></div>
  <div class="page">
    <table class="table tb-type2 order">
      <tbody>
      <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
        <th>订单信息</th>
      </tr>
      <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
        <td colspan="2"><ul>
          <li>
            <strong>订单号:</strong>${orderDetailDTO.orderId}
          </li>
          <li><strong>订单状态:</strong>${orderDetailDTO.stateValue}</li>
          <li><strong>订单总额:</strong><span class="red_common">${orderDetailDTO.productTotalPrice}</span>
          </li>
          <li><strong>运费:</strong>￥${orderDetailDTO.deliverFee}</li>
        </ul></td>
      </tr>
      <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
        <td><ul>
          <li><strong>买家：</strong>${orderDetailDTO.createBy}</li>
          <li><strong>店铺：</strong>${orderDetailDTO.channelName}</li>
          <li><strong>支付方式：</strong>${orderDetailDTO.paymentWay}</li>
          <li><strong>下单时间：</strong>${orderDetailDTO.createTime}</li>
        </ul></td>
      </tr>
      <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
        <th>收货人信息</th>
      </tr>
      <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
        <td><ul>
          <li><strong>收货人姓名：</strong>${orderDetailDTO.deliver.recipients}</li>
          <li><strong>电话号码：</strong>${orderDetailDTO.deliver.mobile}  ${orderDetailDTO.deliver.tel}</li>
          <li><strong>详细地址：</strong>${orderDetailDTO.deliver.province} ${orderDetailDTO.deliver.city} ${orderDetailDTO.deliver.district} ${orderDetailDTO.deliver.detail}</li>
        </ul></td>
      </tr>

      <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
        <td><table class="table tb-type2 goods ">
          <tbody>
          <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
            <th>商品信息</th>
            <th class="align-center">单价</th>
            <th class="align-center">实际支付额</th>
            <th class="align-center">数量</th>
            <th class="align-center">佣金比例</th>
            <th class="align-center">收取佣金</th>
          </tr>
          <c:choose>
            <c:when test="${orderDetailDTO.items != null && orderDetailDTO.items.size() != 0}">
              <c:forEach var="item" items="${orderDetailDTO.items}">
                <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
                 <td class="w50pre"><p><a target="_blank" href=""><img img width="60" src="${item.imageUrl}" alt=""> </a><a target="_blank" href="">${item.productName}</a></p><p></p></td>
                  <td class="w96 align-center"><span class="red_common"><c:out value="${item.productPrice}"/></span></td>
                  <td class="w96 align-center"><span class="red_common"><c:out value="${item.productPrice * item.amount}"/></span></td>
                  <td class="w96 align-center"><span class="red_common"><c:out value="${item.amount}"/></span></td>
                  <td class="w96 align-center">0</td>
                  <td class="w96 align-center">0</td>
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
        <td><a onclick="history.go(-1)" class="btn" href="JavaScript:void(0);"><span>返回</span></a></td>
      </tr>
      </tfoot>
    </table>
  </div>
  </body>
</html>
