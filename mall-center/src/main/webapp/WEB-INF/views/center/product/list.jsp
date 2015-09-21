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
    <td><select name="上架状态" class="input-text" id="上架状态"  style="width:100px; ">
    <option selected>全部商品</option>
              <option selected>已上架</option>
              <option>已下架</option>
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
    <td><input type="text" name="" id="" placeholder=" 请输入关键字、商品货号" style="width:250px" class="input-text"></td>

    <td><button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜产品</button></td>
  </tr>
</table>

            
            
			
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="product_add('添加产品','product-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加产品</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
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
				<!-- 	<tr class="text-c va-m">
						<td><input name="" type="checkbox" value=""></td>
						<td>001</td>
						<td><a onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img width="60" class="product-thumb" src="pic/product/Thumb/6204.jpg"></a></td>
						<td class="text-l"><a style="text-decoration:none" onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img title="国内品牌" src="images/gq/cn.gif"> <b class="text-success">圣象</b> 哥本哈根橡木地板KS8373</a></td>
						<td class="text-l">原木的外在,实木条形结构,色泽花纹自然,写意;款式设计吸取实木地板的天然去雕饰之美,在视觉上给人带来深邃联想.多款产品适合搭配不同的风格的室内装饰;功能流露出尊贵典雅的大气韵味。</td>
						<td><span class="price">356.0</span> 元/平米</td>
						<td class="td-status"><span class="label label-success radius">已发布</span></td>
						<td class="td-manage"><a style="text-decoration:none" onClick="product_stop(this,'10001')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> <a style="text-decoration:none" class="ml-5" onClick="product_edit('产品编辑','product-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr> -->
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
var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				demoIframe.attr("src",treeNode.file + ".html");
				return true;
			}
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"一级分类", open:true},
	{ id:11, pId:1, name:"二级分类"},
	{ id:111, pId:11, name:"三级分类"},
	{ id:112, pId:11, name:"三级分类"},
	{ id:113, pId:11, name:"三级分类"},
	{ id:114, pId:11, name:"三级分类"},
	{ id:115, pId:11, name:"三级分类"},
	{ id:12, pId:1, name:"二级分类 1-2"},
	{ id:121, pId:12, name:"三级分类 1-2-1"},
	{ id:122, pId:12, name:"三级分类 1-2-2"},
];
		
var code;
		
function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}
		
/* $(document).ready(function(){
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	demoIframe.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id",'11'));
});
 */
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	/* "aoColumnDefs": [
	  {"orderable":false,"aTargets":[0,7]}// 制定列不参与排序
	] */
	   "bProcessing": true,
       "bServerSide": true,
       "bStateSave": false,
       "aLengthMenu":[[1, 5, 15, 30], [1, 5, 15, 30]],
       "sAjaxSource": "${pageContext.request.contextPath}/product/findByChannelId.dhtml",
       "sAjaxDataProp":"content",
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
           //alert('row = '+row+'mDataProp = ' +mDataProp +'dataIndex = '+dataIndex);
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
   								return '<td><div class="td-manage"><a style="text-decoration:none" onClick="product_stop(this,'+mDataProp.id+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a></a><a style="text-decoration:none" class="ml-5" onClick="product_edit(\'产品编辑\',\'forword.dhtml\','+mDataProp.id+')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a><a style="text-decoration:none" class="ml-5" onClick="product_del(this,'+mDataProp.id+')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></div></td>';
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
		alert($(obj).parents("tr").find(".td-manage").html());
		
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}

/*图片-发布*/
function product_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
	alert($(obj).parents("tr").find(".td-status").html());
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
		content: url
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