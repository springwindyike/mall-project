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
  <tr>
    <td><select name="上架状态" class="input-text" id="上架状态"  style="width:100px; ">
    <option selected>全部订单来源</option>
              <option selected>享买自营</option>
              <option>和小宝</option>
          </select></td>
                <td><select name="" style="width:100px; margin:0 10px; " class="input-text">
              <option>全部分类</option>
              <option>手机</option>
              <option>相机</option>
            </select></td>
            <td><select name="" style="width:100px;margin:0 10px; " class="input-text">
              <option>全部品牌</option>
              <option>享买</option>
              <option>锋果</option>
            </select></td>
    <td><input type="text" name="" id="searchCondition" placeholder=" 请输入关键字、订单号" style="width:250px" class="input-text"></td>

    <td><button name="" id="" class="btn btn-success" type="submit" onclick="searchOrder();"><i class="Hui-iconfont">&#xe665;</i> 搜订单</button></td>
  </tr>
</table>


		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="110">订单号</th>
						<th width="70">商品图片</th>
						<th width="120">商品名称</th>
						<th width="90">单价（元）</th>
						<th width="50">数量</th>
						<th width="88">购买账号</th>
						<th width="80">收货人</th>
						<th>顾客留言</th>
						<th width="90">付款（元）</th>
						<th width="80">生成时间</th>
						<th width="80">订单状态</th>
						<th width="110">操作</th>
					</tr>
				</thead>
				<tbody>
					
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
var url = "${pageContext.request.contextPath}/order/findByChannelId.dhtml";
$(function () {
	targetTable = $('.table-sort').DataTable({
/* 		"aaSorting": [[ 0, "desc" ]],//默认第几个排序 */
/* 	 	"bStateSave": true,//状态保存 */
		"bProcessing": true,
		"bServerSide": true,
		"bStateSave": false,
		"aLengthMenu":[[2, 5, 15, 30], [2, 5, 15, 30]],
   "ajax": {
       url:url,
       "dataSrc": "content"
        },
		"aoColumns": [
		   { "mDataProp": "orderId" },//订单号
		   { "mDataProp": null },//商品图片
		   { "mDataProp": null },//商品名称
		   { "mDataProp": null },//单价（元）
		   { "mDataProp": null },//数量
		   { "mDataProp": "createBy" },//购买账号
		   { "mDataProp": "recipients" },//收货人
		   { "mDataProp": "note" },//顾客留言
		   { "mDataProp": "totalPrice" },//付款（元）	
		   { "mDataProp": "createTime" },//生成时间	
		   { "mDataProp": null },  //订单状态
		   { "mDataProp": null }//操作
		],
		
		"createdRow" : function(row, mDataProp, dataIndex){
		   $(row).addClass('text-c');
		},
		
		"columnDefs" : [
			{
				"targets" : 1 ,
				"render" : function(mDataProp, type, full) {
					return '<a onClick="product_show(\'哥本哈根橡木地板\',\'product-show.html\',\'10001\')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a>';
				}
			},
			{
				"targets" : 2 ,
				"render" : function(mDataProp, type, full) {
					var itemHtml = "";
					for (var i = 0; i < mDataProp.items.length; i++) 
						{
						itemHtml = mDataProp.items[i].productName;
						}
					return '<a style="text-decoration:none" onClick="product_show(\''+itemHtml+'\',\'product-show.html\',\'10001\')" href="javascript:;">'+itemHtml+'</a>';
				}
			},
			{
				"targets" : 3 ,
				"render" : function(mDataProp, type, full) {
					var itemHtml = "";
					for (var i = 0; i < mDataProp.items.length; i++) 
						{
						itemHtml = mDataProp.items[i].productPrice;
						}
					return itemHtml;
					}
			},
			{
				"targets" : 4 ,
				"render" : function(mDataProp, type, full) {
					var itemHtml = "";
					for (var i = 0; i < mDataProp.items.length; i++) 
						{
						itemHtml = mDataProp.items[i].amount;
						}
					return itemHtml;
				}
			},
			{
				"targets" : 10 ,
				"render" : function(mDataProp, type, full) {
					var itemHtml = mDataProp.stateValue;
					if(itemHtml == '已取消' || itemHtml == '待审核' ){
						return '<span class="outspan"><span class="label label-defaunt radius">'+itemHtml+'</span></span>';
					}
					return '<span class="outspan"><span class="label label-success radius">'+itemHtml+'</span></span>'; 
					/* return itemHtml; */
				 }
			} ,
			{
				"targets" : 11 ,
				"orderable":false,
				"aTargets":[11],
				"render" : function(mDataProp, type, full) {
					var itemHtml = mDataProp.state;
					if(itemHtml == '待审核' ){
						return '<td class="td-manage">'
						+'<a style="text-decoration:none" class="ml-5" onClick="order_edit(\'订单编辑\',\'${pageContext.request.contextPath}/order/edit.dhtml\',\'10001\')" href="javascript:;" title="编辑">'
						+'<i class="Hui-iconfont">&#xe6df;</i></a>&nbsp;&nbsp;'
						+'<a style="text-decoration:none" class="ml-5" onClick="order_deliver(\'发货\',\'${pageContext.request.contextPath}/order/deliver/'+mDataProp.orderId+'.dhtml\',\'10001\')" href="javascript:;" title="现在发货">'
						+'<i class="Hui-iconfont">&#xe634;</i></a>&nbsp;&nbsp;'
						+'<a style="text-decoration:none" class="ml-5" onClick="order_logistics(\'物流\',\'${pageContext.request.contextPath}/order/logistics.dhtml\',\'10001\')" href="javascript:;" title="查询物流">'
						+'<i class="Hui-iconfont">&#xe669;</i></a>&nbsp;&nbsp;'
						+'<a style="text-decoration:none" class="ml-5" onClick="order_cancel(\'取消订单\',\'${pageContext.request.contextPath}/order/cancel/'+mDataProp.orderId+'.dhtml\',\'10001\')" href="javascript:;" title="取消订单">'
						+'<i class="Hui-iconfont">&#xe608;</i></a>&nbsp;&nbsp;'
						+'<a style="text-decoration:none" class="ml-5" onClick="order_verify(this,\'10001\')" title="通过审核">'
						+'<i class="Hui-iconfont">&#xe6e1;</i></a></td>';
					}
					return '<td class="td-manage">'
					+'<a style="text-decoration:none" class="ml-5" onClick="order_edit(\'订单编辑\',\'${pageContext.request.contextPath}/order/edit.dhtml\',\'10001\')" href="javascript:;" title="编辑">'
					+'<i class="Hui-iconfont">&#xe6df;</i></a>&nbsp;&nbsp;'
					+'<a style="text-decoration:none" class="ml-5" onClick="order_deliver(\'发货\',\'${pageContext.request.contextPath}/order/deliver/'+mDataProp.orderId+'.dhtml\',\'10001\')" href="javascript:;" title="现在发货">'
					+'<i class="Hui-iconfont">&#xe634;</i></a>&nbsp;&nbsp;'
					+'<a style="text-decoration:none" class="ml-5" onClick="order_logistics(\'物流\',\'${pageContext.request.contextPath}/order/logistics.dhtml\',\'10001\')" href="javascript:;" title="查询物流">'
					+'<i class="Hui-iconfont">&#xe669;</i></a>&nbsp;&nbsp;'
					+'<a style="text-decoration:none" class="ml-5" onClick="order_cancel(\'取消订单\',\'${pageContext.request.contextPath}/order/cancel/'+mDataProp.orderId+'.dhtml\',\'10001\')" href="javascript:;" title="取消订单">'
					+'<i class="Hui-iconfont">&#xe608;</i></a></td>';
				}
			} 
		]
		});
	
});
/*根据条件查询*/
function searchOrder(){
    var searchCondition = $("#searchCondition").val();
    url = '${pageContext.request.contextPath}'+'/order/findBySearchCondition/'+searchCondition+'.dhtml';
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
function order_verify(obj,id){
	layer.confirm('确认要审核通过吗？',function(index){
		$(obj).parents("tr").find(".outspan").html('<span class="label label-success radius">等待付款</span>');
		$(obj).remove();
		layer.msg('等待付款!',{icon: 6,time:1000});
	});
}


</script>
</body>