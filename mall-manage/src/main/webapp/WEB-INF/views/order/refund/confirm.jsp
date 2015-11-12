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
  <link href="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css" id="cssfile2" />
  <title>订单详情</title>
</head>
<body>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>

<form id="post_form" method="post" action="${pageContext.request.contextPath}/order/go2Confirm/${orderRefundDTO.refundId}.dhtml">
  <input type="hidden" name="form_submit" value="ok" />
  <table class="table tb-type2">
    <tbody>
    <tr>
      <td colspan="2" class="required">退款金额：</td>
    </tr>
    <tr class="noborder">
      <td class="vatop rowform">${orderRefundDTO.refundAmount}</td>
      <td class="vatop tips"></td>
    </tr>
    <tr>
      <td colspan="2" class="required">商品名称：</td>
    </tr>
    <tr class="noborder">
      <td class="vatop rowform">${orderRefundDTO.productName}</td>
      <td class="vatop tips"></td>
    </tr>
    <tr>
      <td colspan="2" class="required">退款原因：</td>
    </tr>
    <tr class="noborder">
      <td class="vatop rowform">${orderRefundDTO.buyerMessage}</td>
      <td class="vatop tips"></td>
    </tr>

    <tr>
      <td colspan="2" class="required">凭证上传：</td>
    </tr>
    <tr class="noborder">
      <td class="vatop rowform"><img width="60px" alt="" src="${orderRefundDTO.refundPicture}" />
      </td>
      <td class="vatop tips"></td>
    </tr>
    <tr>
      <td colspan="2" class="required">商家备注：</td>
    </tr>
    <tr class="noborder">
      <td class="vatop rowform">${orderRefundDTO.centerStateStr}</td>
      <td class="vatop tips"></td>
    </tr>
    <tr>
      <td colspan="2" class="required"><label class="validation">备注信息：</label></td>
    </tr>
    <tr class="noborder">
      <td class="vatop rowform">
        <textarea id="admin_message" name="admin_message" class="tarea"></textarea></td>
      <td class="vatop tips"></td>
    </tr>

    </tbody>
    <tfoot>
    <tr class="tfoot">
      <td colspan="15" ><a href="JavaScript:void(0);" class="btn" id="submitBtn"><span>提交</span></a></td>
    </tr>
    </tfoot>
  </table>
</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/jquery.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/jquery.validation.min.js" charset="utf-8"></script>
<script type="text/javascript">
  $(function(){
    $("#submitBtn").click(function(){
      if($("#post_form").valid()){
        $("#post_form").submit();
      }
    });
    $('#post_form').validate({
      errorPlacement: function(error, element){
        error.appendTo(element.parent().parent().prev().find('td:first'));
      },
      rules : {
        admin_message : {
          required   : true
        }
      },
      messages : {
        admin_message  : {
          required   : '备注信息不能为空'
        }
      }
    });
  });
</script>
</body>
</html>
