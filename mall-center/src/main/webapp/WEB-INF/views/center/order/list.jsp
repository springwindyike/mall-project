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
		<!-- <div class="cl pd-5 bg-1 bk-gray mt-20"> --><!--  <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span>  --><!-- <span class="r">共有数据：<strong>54</strong> 条</span> </div> -->
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<!-- <th width="40"><input name="" type="checkbox" value=""></th> -->
						<th width="110">订单号</th>
						<th width="70">商品图片</th>
						<th width="120">商品名称</th>
<!-- 						<th>商品来源</th>
						<th width="110">联系方式</th> -->
						<th width="90">单价（元）</th>
						<th width="50">数量</th>
						<th width="88">购买账号</th>
						<th width="80">收货人</th>
						<th>顾客留言</th>
						<th width="90">付款（元）</th>
						<th width="80">生成时间</th>
						<th width="80">订单状态</th>
						<!-- <th width="70">操作</th> -->
					</tr>
				</thead>
				<tbody>
					<!-- <tr class="text-c va-m"> -->
						<!-- <td rowspan="2"><input name="" type="checkbox" value=""></td> -->
<%-- 						<td>20150825000002</td>
						
						<td><a onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a></td>
						<td class="text-l"><a style="text-decoration:none" onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><b class="text-success">圣象</b> 哥本哈根橡木地板KS8373</a></td>
						<td>小明</td>
						<td>要大一号的，发顺丰。</td>
						<td>121.1元</td>
						<td>3</td>
						<td>2015-08-25 19:22:17</td>
						<td>热的方</td>
						<td><span class="price">356.0</span> 元</td>
						<td class="td-status"><span class="label label-success radius">已发布</span></td>
						<td class="td-manage"> <a style="text-decoration:none" class="ml-5" onClick="product_edit('订单编辑','product-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a><!--  <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a> --></td>
					</tr> --%>
<%-- 					<tr class="text-c va-m">
						<td><a onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a></td>
						<td class="text-l"><a style="text-decoration:none" onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><b class="text-success">圣象</b> 哥本哈根橡木地板KS8373</a></td>
						<td>小明</td>
						<td>要大一号的，发顺丰。</td>
						<td>121.1元</td>
						<td>3</td>
					</tr> --%>
					
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
			   { "mDataProp": null }  //订单状态
			   //{ "mDataProp": null }//操作
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
					 }
				}/* ,
				{
					"targets" : 11 ,
					"orderable":false,
					"aTargets":[11],
					"render" : function(mDataProp, type, full) {
						return '<td class="td-manage"> <a style="text-decoration:none" class="ml-5" onClick="product_edit(\'订单编辑\',\'product-add.html\',\'10001\')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a></td>';
					}
				} */
			]
			});
	
	$('.table-sort tbody').on('click', 'tr', function () {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		}
		else {
			$('table tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
});	
/*根据条件查询*/
function searchOrder(){
    var searchCondition = $("#searchCondition").val();
    url = '${pageContext.request.contextPath}'+'/order/findBySearchCondition/'+searchCondition+'.dhtml';
    targetTable.ajax.url(url).load();

}

/*图片-添加*/
/* function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
} */
/*图片-查看详情*/
/* function product_show(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
} */
/*图片-审核*/
/* function product_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过'], 
		shade: false
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
} */
/*图片-下架  在页面已删除 可改为订但相关*/
/* function product_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
} */

/*图片-发布 在页面已删除 可改为订但相关*/
/* function product_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
} */
/*图片-申请上线 在页面已删除 可改为订但相关*/
/* function product_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
} */
/*图片-编辑*/
/* function product_edit(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
} */
/*图片-删除*/
/* function product_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
} */
</script>
</body>