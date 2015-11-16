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
      <th colspan="2">退款明细</th>
    </tr>
    <tr>
      <td colspan="2"><ul>
        <li>
          <strong>退款单号：:</strong>${orderRefundDTO.refundId}
        </li>
      </ul></td>
    </tr>
    <tr>
      <td><ul>
        <li><strong>商品名称：</strong>${orderRefundDTO.productName}</li>
      </ul></td>
    </tr>
    <tr>
      <td><ul>
        <li><strong>退款金额：</strong>${orderRefundDTO.refundAmount}</li>
      </ul></td>
    </tr>
    <tr>
    <tr>
      <td><ul>
        <li><strong>退款金额：</strong>${orderRefundDTO.refundAmount}</li>
      </ul></td>
    </tr>
    <tr>
      <td><ul>
        <li><strong>退款原因：</strong>${orderRefundDTO.buyerMessage}</li>
      </ul></td>
    </tr>
    <tr>
      <td><ul>
        <li><strong>凭证：</strong><img width="60px" alt="" src="${orderRefundDTO.refundPicture}" /></li>
      </ul></td>
    </tr>
    <tr>
      <td><ul>
        <li><strong>商家处理：</strong>${orderRefundDTO.centerStateStr}</li>
      </ul></td>
    </tr>
    <tr>
      <td><ul>
        <li><strong>商家备注：</strong>${orderRefundDTO.sellerMessage}</li>
      </ul></td>
    </tr>
    <c:if test="${orderRefundDTO.centerState == 2}">
      <tr>
        <td><ul>
          <li><strong>平台处理：</strong>${orderRefundDTO.refundStateStr}</li>
        </ul></td>
      </tr>
      <tr>
        <td><ul>
          <li><strong>平台备注：</strong>${orderRefundDTO.adminMessage}</li>
        </ul></td>
      </tr>
    </c:if>
    </tbody>
    <tfoot>
    <tr class="tfoot">
      <td><a href="${pageContext.request.contextPath}/order/orderRefundMoney.dhtml" class="btn" ><span>返回</span></a></td>
    </tr>
    </tfoot>
  </table>
</div>
</body>
</html>
