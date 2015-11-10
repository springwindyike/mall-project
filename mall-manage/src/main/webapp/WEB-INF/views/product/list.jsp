<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/zTreeStyle.css" type="text/css">
<title>产品列表</title>
</head>
<body class="pos-r">
<div >
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 产品列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div class="text-c"> 
			<table  style="width:auto;" border="0">
  <tr>
    <td><select name="上架状态" class="input-text" id="上架状态"  style="width:100px; " id="searchStatus">
   						  <option selected>全部商品</option>
              <option >已上架</option>
              <option>已下架</option>
     </select></td>
   <td><select name="" style="width:100px; margin:0 10px; " class="input-text" id ="searchSort">
             <option>全部分类</option>
             <option>手机</option>
             <option>相机</option>
   </select></td>
            <td><select name="" style="width:100px;margin:0 10px; " class="input-text" id ="searchBrand">
              <option>全部品牌</option>
              <option>享买</option>
              <option>锋果</option>
            </select></td>
    <td><input type="text" name="" id="searchProductId" placeholder=" 请输入商品货号" style="width:150px" class="input-text"></td>
     <td><input type="text" name="" id="searchProductName" placeholder=" 请输入商品名称" style="width:150px" class="input-text"></td>

    <td><button name="" id="" class="btn btn-success" type="submit" onclick="searchProduct();"><i class="Hui-iconfont">&#xe665;</i> 搜产品</button></td>
  </tr>
</table>

            
            
			
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="40"><input name="" type="checkbox" value=""></th>
						<th width="70">产品ID号</th>
						<th width="80">缩略图</th>
						<th width="100">产品名称</th>
						<th>描述</th>
						<th width="100">商品进价</th>
							<th width="100">市场价</th>
						<th width="60">发布状态</th>
						<th width="100">操作</th>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script> 
<script type="text/javascript">
var url = "${pageContext.request.contextPath}/product/findAll.dhtml";
$(function () {
	 targetTable = $('.table-sort').DataTable({
	"bStateSave": true,//状态保存

	   "bProcessing": true,
       "bServerSide": true,
       "bStateSave": false,
       "aLengthMenu":[[2, 5, 15, 30], [2, 5, 15, 30]],
   	"ajax": {
		   url:url,
		   "dataSrc": "content"
		    },
       "aoColumns": [
           { "mDataProp": null },
           { "mDataProp": "id" },
           { "mDataProp": null },
           { "mDataProp": "name" },
           { "mDataProp": "description" },
           { "mDataProp": "basePrice" },
           { "mDataProp": "marketPrice" },
           { "mDataProp": null },
           { "mDataProp": null },
       ],

       "createdRow" : function(row, mDataProp, dataIndex){
           $(row).addClass('text-c');
       },

       "columnDefs" : [
               {
                   "targets" : 0 ,
                   "render" : function(mDataProp, type, full) {
                   return '<tr class="text-c"><td ><input type="checkbox" value="1" name="" ></td></tr>';
               }
           },

              {
                   "targets" : 2 ,
                   "render" : function(mDataProp, type, full) {
                	   return '<td><a onClick="product_show(\'哥本哈根橡木地板\',\'product-show.html\',\'10001\')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a></td>';
               }
           }, 
           {
               "targets" : 7 ,
               "render" : function(mDataProp, type, full) {
                   return '<td><span class="label td-status label-success radius">已启用</span></td>';
               }
           },

           {
               "targets" : 8 ,
               "render" : function(mDataProp, type, full) {
   								return '<td><div class="td-manage"><a style="text-decoration:none" onClick="product_stop(this,'+mDataProp.id+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a></a><a style="text-decoration:none" class="ml-5" onClick="product_del(this,'+mDataProp.id+')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></div></td>';
               }
           },
       ],
   });
   $('.table-sort tbody').on('click', 'tr', function () {
       if ($(this).hasClass('selected')) {
           $(this).removeClass('selected');
       }
       else {
           $('tr.selected').removeClass('selected');
           $(this).addClass('selected');
       }
});
});
   /*根据条件查询*/
/*    function searchProduct(){
       var searchCondition = $("#searchCondition").val();
       url = '${pageContext.request.contextPath}'+'/product/findBySearchCondition/'+searchCondition+'.dhtml';
       targetTable.ajax.url(url).load();

   } */
   /*根据条件查询*/
   function searchProduct(){
   	url = '${pageContext.request.contextPath}'+'/product/findBySearchCondition.dhtml?'
   	var status = $("#searchStatus").val();
   	var name = $("#searchProductName").val();
   	var id = $("#searchProductId").val();
   	var brandName = $("#searchBrand").val();
   	var sort = $("#searchSort").val();
   	if(status != ""){
   		url = url+'status='+status+'&';
   	}
   	if(name != ""){
   		url = url + 'name='+name+'&';
   	}
   	if(id!="0"){
   		url = url + 'id='+id+'&';
   	}
   	if(brandName!=""){
   		url = url + 'brandName='+brandName+'&';
   	}
   	if(sort!=""){
   		url = url + 'sort='+sort+'&';
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
/*图片-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-查看*/
function product_show(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-审核*/
function product_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过'], 
		shade: false
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:7,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}
/*图片-下架*/
function product_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}

/*图片-发布*/
function product_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
}
/*图片-申请上线*/
function product_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}
/*图片-编辑*/
function product_edit(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: 'update/'+id+'.dhtml'
	});
	layer.full(index);
}
/*图片-删除*/
function product_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			  url: 'del/'+id+'.dhtml',
			});
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
</script>
</body>
</html>