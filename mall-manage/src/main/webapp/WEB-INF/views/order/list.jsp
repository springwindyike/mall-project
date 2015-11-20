<%--
  Created by IntelliJ IDEA.
  User: ZhangZhaoxin
  Date: 2015/9/14
  Time: 17:33
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>『享买』开放平台v1.0</title>
		<meta name="keywords" content="享买开放平台">
		<meta name="description" content="享买开放平台">
</head>

<body class="pos-r">
<div >
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div class="text-c"> 
			<table  style="width:auto;" border="0">

  <tr class="text-c">
	  <th>订单号:</th>
	  <td><input type="text" name="" id="searchOrderId" placeholder="订单号" style="width:250px" class="input-text"></td>
	  <th>渠道名称:</th>
	  <td><input type="text" name="" id="searchChannelName" placeholder="渠道名称" style="width:250px" class="input-text"></td>
	  <th>订单状态:</th>
	  <td>
		  <select name="status" style="width:100px; margin:0 10px;" class="input-text" id="searchStatus">
			  <option  value="0" selected>订单状态</option>
			  <option value="CANCEL">已取消</option>
			  <option value="WAIT_CONFIRM">待审核</option>
			  <option value="WAIT_PAYMENT">等待付款</option>
			  <option value="WAIT_DELIVER">等待发货</option>
			  <option value="DELIVERED">已发货</option>
			  <option value="RECEIVED">已收货</option>
			  <option value="WAIT_RECEIVED">待收货</option>
		  </select>
	  </td>

  </tr>
  <tr class="text-c">
	  <th>下单时间:</th>
	<td>
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:118px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:118px;">
	</td>
	  <th>买家:</th>
	<td><input type="text" name="" id="searchCreateBy" placeholder="买家" style="width:250px" class="input-text"></td>
	  <th>支付方式:</th>
	  <td>
		<select name="payWay" style="width:100px; margin:0 10px;" class="input-text" id="payWay">
			<option  value="0" selected>支付方式</option>
			<option value="NET">网上支付</option>
			<option value="COD">货到付款</option>
			<option value="BANKREMITTANCE">银行电汇</option>
			<option value="POSTOFFICEREMITTANCE">邮局汇款</option>
		</select>
	</td>
	  <td><button name="" id="" class="btn btn-success" type="submit" onclick="searchOrder();"><i class="Hui-iconfont">&#xe665;</i>搜索</button></td>
  </tr>
</table>


		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="110">订单号</th>
						<th width="70">渠道</th>
						<th width="88">买家</th>
						<th width="88">总价</th>
						<th width="88">支付方式</th>
						<th width="80">下单时间</th>
						<th width="80">订单状态</th>
						<th width="110">操作</th>
					</tr>
				</thead>
				<tbody>
				<input id="deliverStatus" type="hidden" value="${status}"/>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script> 
<script type="text/javascript">	
var targetTable;
var url = "${pageContext.request.contextPath}/order/getOrderList.dhtml";
	var deliverStatus = $("#deliverStatus").val();
	if(deliverStatus=="true"){
		alert("发货成功");
}
$(function(){

});
$(function () {
	targetTable = $('.table-sort').DataTable({
		"oLanguage": {
			"sInfoEmpty": "没有数据",
			"sZeroRecords": "没有数据",
			"sEmptyTable":"没有数据"
		},
		"searching": false,
		"ordering":  false,
		"bProcessing": true,
		"bServerSide": true,
		"bStateSave": false,
		"aLengthMenu":[[5, 7, 15, 30], [5, 7, 15, 30]],
   "ajax": {
       url:url,
       "dataSrc": "content"
        },
		"aoColumns": [
		   { "mDataProp": "orderId" },
		   { "mDataProp": "channelName" },
		   { "mDataProp": "createBy" },
		   { "mDataProp": "productTotalPrice" },
		   { "mDataProp": "paymentWay" },
		   { "mDataProp": "createTime" },
		   { "mDataProp": null },
		   { "mDataProp": null }
		],
		
		"createdRow" : function(row, mDataProp, dataIndex){
		   $(row).addClass('text-c');
		},
		
		"columnDefs" : [

			{
				"targets" : 6 ,
				"render" : function(mDataProp, type, full) {
					var itemHtml = mDataProp.stateValue;
					if(itemHtml == '已取消' ){
						return '<span class="outspan"><span class="label label-defaunt radius">'+itemHtml+'</span></span>';
					}
//					if(itemHtml == '待审核'){
//						return '<span class="outspan"><span class="label label-warning radius">'+itemHtml+'</span></span>';
//					}
					return '<span class="outspan"><span class="label label-success radius">'+itemHtml+'</span></span>';
				 }
			} ,
			{
				"targets" : 7 ,
				"orderable":false,
				"aTargets":[7],
				"render" : function(mDataProp, type, full) {
					var itemHtml = mDataProp.stateValue;
					var str = '<td class="td-manage">';
					if(itemHtml == '已取消'){
						str = str + '<a style="text-decoration:none" class="ml-5"  href="${pageContext.request.contextPath}/order/getOrderDetail/'+mDataProp.orderId+'.dhtml" title="订单详情">'
								+'<i class="Hui-iconfont">&#xe695;</i></a>&nbsp;&nbsp;'+'</td>';
					}
					if(itemHtml == "待收货" || itemHtml == "已发货" || itemHtml == "已收货"){
						str = str + '<a style="text-decoration:none" class="ml-5"  href="${pageContext.request.contextPath}/order/getOrderDetail/'+mDataProp.orderId+'.dhtml" title="订单详情">'
								+'<i class="Hui-iconfont">&#xe695;</i></a>&nbsp;&nbsp;'+
								'<a style="text-decoration:none" class="ml-5" onClick="order_logistics(\'物流\',\'${pageContext.request.contextPath}/order/logistics/'+ mDataProp.expressOrder + "/"+ mDataProp.expressId +'.dhtml\',\'10001\')" href="javascript:;" title="查询物流">'
								+'<i class="Hui-iconfont">&#xe669;</i></a>&nbsp;&nbsp;'+'</td>';
					}

					if(itemHtml =="等待发货" || itemHtml =="等待付款"){
						str = str +'<a style="text-decoration:none" class="ml-5" onClick="order_edit(\'订单编辑\',\'${pageContext.request.contextPath}/order/edit/'+mDataProp.orderId+'.dhtml\',\'10001\')" href="javascript:;" title="编辑">'
						+'<i class="Hui-iconfont">&#xe6df;</i></a>&nbsp;&nbsp;'
						+'<a style="text-decoration:none" class="ml-5"  href="${pageContext.request.contextPath}/order/getOrderDetail/'+mDataProp.orderId+'.dhtml" title="订单详情">'
						+'<i class="Hui-iconfont">&#xe695;</i></a>&nbsp;&nbsp;'
						+'<a style="text-decoration:none" class="ml-5" href="${pageContext.request.contextPath}/order/deliver/'+mDataProp.orderId+'.dhtml" title="现在发货">'
						+'<i class="Hui-iconfont">&#xe634;</i></a>&nbsp;&nbsp;'
						+'<a style="text-decoration:none" class="ml-5" onClick="order_cancel(\'取消订单\',\'${pageContext.request.contextPath}/order/cancel/'+mDataProp.orderId+'.dhtml\',\'10001\')" href="javascript:;" title="取消订单">'
						+'<i class="Hui-iconfont">&#xe608;</i></a>&nbsp;&nbsp;'	+'</td>';
					}

					if(itemHtml =="待审核"){
						str = str +'<a style="text-decoration:none" class="ml-5" onClick="order_verify(\'${pageContext.request.contextPath}/order/confirmOrder/'+mDataProp.orderId+'.dhtml\')" href="javascript:;" title="审核订单">'
								+'<i class="Hui-iconfont">&#xe615;</i></a>&nbsp;&nbsp;'
					}
					return str;
				}
			}
		]
		});
});
/*根据条件查询*/
function searchOrder(){
	url = '${pageContext.request.contextPath}'+'/order/findBySearchCondition.dhtml?'
	var orderId = $("#searchOrderId").val();
	var channelName = $("#searchChannelName").val();
	var status = $("#searchStatus").val();
	var datemin = $("#datemin").val();
	var datemax = $("#datemax").val();
	var createBy = $("#searchCreateBy").val();
	var payWay = $("#payWay").val();
	if(orderId != ""){
		url = url+'orderId='+orderId+'&';
	}
	if(channelName != ""){
		url = url + 'channelName='+channelName+'&';
	}
	if(status!="0"){
		url = url + 'status='+status+'&';
	}
	if(datemin!=""){
		url = url + 'datemin='+datemin+'&';
	}
	if(datemax!=""){
		url = url + 'datemax='+datemax+'&';
	}
	if(createBy!=""){
		url = url + 'createBy='+createBy+'&';
	}
	if(payWay!="0"){
		url = url + 'payWay='+payWay
	}
    targetTable.ajax.url(url).load();

}
	
	$('.table-sort tbody').on('click', 'tr', function () {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		}
		else {
			$('table tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	

/*图片-编辑*/
function order_edit(title,url,id){
	var index = layer.open({
		type: 2,
		area: ['700px', '530px'],
		title: title,
   fix: false, //不固定
   maxmin: true,
		content: url
	});
	//layer.full(index);
}
/*图片-发货*/
function order_deliver(title,url,id){
	var index = layer.open({
		type: 2,
		area: ['700px', '330px'],
		title: title,
   fix: false, //不固定
   maxmin: true,
		content: url
	});
	//layer.full(index);
}
/*图片-物流*/
function order_logistics(title,url,id){
	var index = layer.open({
		type: 2,
		area: ['700px', '530px'],
		title: title,
   fix: false, //不固定
   maxmin: true,
		content: url
	});
	//layer.full(index);
}
/*图片-取消*/
function order_cancel(title,url,id){
	var index = layer.open({
		type: 2,
		area: ['700px', '330px'],
		title: title,
   fix: false, //不固定
   maxmin: true,
		content: url
	});
	//layer.full(index);
}
/*图片-删除*/
/* function product_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
} */

/*审核-通过*/
function order_verify(url){
	if(confirm("确认审核通过？")){
		$.post(
				url,
				function(data) {
					alert("审核通过");
					window.location.reload();
				}
		)
	}
}


</script>
</body>