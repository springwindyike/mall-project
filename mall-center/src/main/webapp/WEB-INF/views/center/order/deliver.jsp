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
<link href="${pageContext.request.contextPath}/resources/shopnc/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/shopnc/seller_center.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/shopnc/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/shopnc/perfect-scrollbar.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/shopnc/skin_0.css" rel="stylesheet" type="text/css" />
<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>现在发货</title>
		<meta name="keywords" content="享买开放平台">
		<meta name="description" content="享买开放平台">
</head>

<body>
<div class="pd-20">
	<div class="step-title"><em>第一步</em>确认收货信息及交易详情</div>
	<form action="${pageContext.request.contextPath}/order/deliver.dhtml" method="post" class="form form-horizontal" id="form-member-deliver">
		<table class="ncsc-default-table order deliver">
			<tbody>
			<tr>
				<td colspan="20" class="sep-row"></td>
			</tr>
			<tr>
				<th colspan="20"><span class="fr mr30"></span><span class="ml10">订单编号：${orderDetailDTO.orderId}</span><span class="ml20">下单时间：<em class="goods-time">${orderDetailDTO.createTime}</em></span>
			</tr>

			<c:choose>
				<c:when test="${orderDetailDTO.items != null && orderDetailDTO.items.size() != 0}">
					<input name="orderId" type="hidden" value="${orderDetailDTO.orderId}"/>
					<c:forEach var="item" items="${orderDetailDTO.items}" varStatus="s">

						<tr>
						<td class="bdl w10"></td>
						<td class="w50"><div class="pic-thumb"><a href="" target="_blank"><img src="http://localhost:9528/${item.imageUrl}" width="60px"/></a></div></td>
						<td class="tl"><dl class="goods-name">
							<dt><a target="_blank" href="">${item.productName}</a></dt>
							<dd><strong>￥${item.productPrice}</strong>&nbsp;x&nbsp;<em>${item.amount}</em>件</dd>
						</dl>
						</td>
						<c:if test="${s.index == 0}">
							<td class="bdl bdr order-info w500" rowspan="${orderDetailDTO.items.size()}">
								<dl>
									<dt>运费：</dt>
									<dd>
										<c:if test="${orderDetailDTO.deliverFee == null || orderDetailDTO.deliverFee == 0}">（免运费）</c:if>
										<c:if test="${orderDetailDTO.deliverFee != null || orderDetailDTO.deliverFee != 0}">￥${orderDetailDTO.deliverFee}</c:if>
									</dd>
								</dl>
								<dl>
									<dt>发货备忘：</dt>
									<dd>
									<div class="row cl">
										<div class="formControls col-5">
											<textarea name="note" cols="" rows="" class="textarea"  placeholder="最多输入100个字符" datatype="*0-100" dragonfly="true" nullmsg="" onKeyUp="textarealength(this,100)"></textarea>
											<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
										</div>
										<div class="col-4"> </div>
									</div>
									</dd>
								</dl>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				没有数据
			</c:otherwise>
			</c:choose>
			<td colspan="20" class="tl bdl bdr" style="padding:8px" id="address"><strong class="fl">收货人信息：</strong><span id="buyer_address_span">${orderDetailDTO.deliver.recipients}&nbsp;
				${orderDetailDTO.deliver.mobile} &nbsp;&nbsp; ${orderDetailDTO.deliver.tel}&nbsp;
				${orderDetailDTO.deliver.province}&nbsp;&nbsp;${orderDetailDTO.deliver.city}&nbsp;&nbsp;${orderDetailDTO.deliver.district}&nbsp;&nbsp;${orderDetailDTO.deliver.detail}
			</span></td>
			</tr>
			</tbody>
		</table>
		<input type="hidden" name="daddress_id" id="daddress_id" value="">
		<div class="step-title mt30"><em>第二步</em>选择物流服务:</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>请选择物流公司：</label>
			<div class="formControls col-5">
	      	<span class="select-box" id="logistics">
	        </span>
		</div>
		<div class="col-4"> </div>
			<div class="row cl" style="float: left">
				<label class="form-label col-3"><span class="c-red">*</span>运单号码：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" autocomplete="off" placeholder="" name="expressOrder" id="expressOrder" datatype="*5-30" nullmsg="运单号码不能为空" >
				</div>
				<div class="col-4"> </div>
				</div>
		</div>
		<tr>
			<div class="row cl">
				<div class="col-9 col-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认发货&nbsp;&nbsp;">
				</div>
			</div>
		</tr>
	</form>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/kuaidi.js"></script>
<script type="text/javascript">

/* 物流选择 */
$(function(){

				var logistics_names = logistics['name'];
				if (!logistics_names) {
						return false;
	      		 }
				var logistics_codes = logistics['id'];
				var $select = $('<select class="select" size="1" datatype="*" nullmsg="请选择物流公司！" style="width:253px">');
				$select.attr('name', 'expressId');
				if (logistics_codes[0] != -1) {
						logistics_names.unshift('请选择');
						logistics_codes.unshift(-1);
	       		}
				for (var idx in logistics_codes) {
						var $option = $('<option>');
						if(logistics_codes[idx] == -1){
							$option.attr('value', "");
						}else{
							$option.attr('value', logistics_codes[idx]);
						}
						$option.text(logistics_names[idx]);
						$select.append($option);
	       		}
				$('#logistics').append($select);
});

//验证
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-deliver").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
		}
	});
});

</script>
</body>
</html>