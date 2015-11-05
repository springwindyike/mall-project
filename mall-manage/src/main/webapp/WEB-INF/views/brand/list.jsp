<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- <meta charset="utf-8"> -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>品牌管理</title>
</head>
<body onLoad="setup()">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 品牌管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<div class="text-c">
		<div  id="result" class="Huiform">
	<img id="uploadImage" src="http://www.firefox.com.cn/favicon.ico">
<input type="file" id="myBlogImage" name="file"/>
<input type="button" value="上传图片" onclick="ajaxFileUpload()"/>
</div>
		<form target="_self" id="add_brand" enctype="multipart/form-data">
			<input type="text" placeholder="品牌名称" value="" id="name" name="name" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
				 <input id ='selectType'  readonly="readonly" class="input-text" value="选择商品分类"  placeholder="" path="typeName" onClick="order_edit()" style="width:120px">
					<input type="hidden" placeholder=""  value="" id="typeId" name="typeId" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
			<select id="s1" name ="country" style ="height:31px" ><option>国家</option></select> 
			<select id="s2" name ="province" style ="height:31px"><option>省份、州</option></select> 
		<select id="s3" name ="city" style ="height:31px"><option>地级市、县</option></select> 
	<select id="s4" name ="district" style ="height:31px"><option>市辖区</option></select> 
			<span class="btn-upload form-group">
				<input type="text" placeholder="具体描述"  value="" id="detail" name="detail" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
				<input type="hidden" placeholder=""  value="" id="logo" name="logo" class="input-text" style="width:120px">
			</span>
			</span><button type="button" class="btn btn-success" id="" name="" onClick="picture_colume_add();"><i class="Hui-iconfont">&#xe600;</i> 添加</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="70">ID</th>
					<th width="100">名称</th>
					<th width="120">LOGO</th>
					<th width="120">国家</th>
					<th width="120">省</th>
					<th width="120">市</th>
					<th width="120">区</th>
					<th>具体描述</th>
					<th width="100">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/ajaxfileupload.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/province.js"></script> 
<script type="text/javascript">
var targetTable;
var url = "${pageContext.request.contextPath}/brand/allBrand.dhtml";
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
        "aLengthMenu":[[2, 5, 15, 30], [2, 5, 15, 30]],
        "ajax": {
            url:url,
            "dataSrc": "content"
        },
         //"sAjaxDataProp":"content",
        "aoColumns": [
                      { "mDataProp": null },
            { "mDataProp": "id" },
            { "mDataProp": "name" },
            { "mDataProp": null },
            { "mDataProp": "country" },
            { "mDataProp": "province" },
            { "mDataProp": "city" },
            { "mDataProp": "district" },
            { "mDataProp": "detail" },
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
                        "targets" : 3 ,
                        "render" : function(mDataProp, type, full) {
                     	   return '<td><a onClick="product_show(\'哥本哈根橡木地板\',\'product-show.html\',\'10001\')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a></td>';
                    }
                }, 

            {
                "targets" : 9,
                "render" : function(mDataProp, type, full) {
                    return '<td class="td-manage"><a title="编辑" href="javascript:;" onclick="brand_edit(\'信息修改\',\'${pageContext.request.contextPath}/brand/update/'+mDataProp.id+'.dhtml\',\'\',\'510\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="brand_del(\'${pageContext.request.contextPath}/brand/del/'+mDataProp.id+'.dhtml\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>  </td>';
}
            },
        ],
    });
});

/*品牌-删除*/
function brand_del(url) {
    if(confirm("确认要删除吗？")){
        $.post(
                url,
                function(data) {
                    alert("删除成功");
                    window.location.reload();
                }
        )
    }
}

/*品牌-编辑*/
function brand_edit(title, url, id, w, h) {
    layer_show(title, url, w, h);
};

function picture_colume_add(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/brand/add.dhtml",
        data:$('#add_brand').serialize(),// 你的formid
        success:function(data, status){ 
        	alert("添加成功");
        	   window.location.href="${pageContext.request.contextPath}/brand/forword.dhtml"
        },
        error:function(data, status, e){ 
            $('#result').html('添加失败，请重试！！');
        }
	});
};
function ajaxFileUpload(){
    $.ajaxFileUpload({
       url:"${pageContext.request.contextPath}/brand/uploadPic.dhtml",
        secureuri:false,                      
        fileElementId:'myBlogImage',         
        dataType:'text',                      
        success:function(data, status){       
            data = data.replace("<PRE>", '');  
            data = data.replace("</PRE>", '');
            data = data.replace("<pre>", '');
            data = data.replace("</pre>", ''); 
            if(data.substring(0, 1) == 0){    
                $("img[id='uploadImage']").attr("src", data.substring(2));
                $('#result').html("图片上传成功<br/>");
                $('#logo').val(data.substring(2));
            }else{
                $('#result').html('图片上传失败，请重试！！');
            }
        },
        error:function(data, status, e){ 
            $('#result').html('图片上传失败，请重试！！');
        } });
};
function order_edit(){
	 var str="";
	 $.ajax({
       type: "get",
       dataType: "json",
       url: "${pageContext.request.contextPath}/productType/firstLevel.dhtml",
       success: function (msg) {
      			 	var jsonData = eval(msg);
		        	$.each(jsonData.child, function(index, jsonOne) {
		         str+="<div><tr id =productName><a onclick=dispaly_child_sort("+jsonOne.id+")>"+jsonOne.typeName+"</a></tr></div>";}	)	;
		        	layer_open(str,'选择分类');
                        }}
		)
};
function layer_open(i,title) {
	layer.closeAll('page');
	var index =layer.open({
	    type: 1,
	    title: title,
	    closeBtn: true,
	    shadeClose: true,
	    area: ['700px', '530px'],
	    content: i
	});
};
function brand_click(id,name){
	layer.closeAll('page');
 	$("#selectBrand").val(name);
 	$("#brandId").val(id);
}; 
var count = 0;
function dispaly_child_sort(parentId){
	 var str="";
	 if (count==2){
			$.ajax({
		        type: "get",
		        dataType: "json",
		        url: "${pageContext.request.contextPath}/productType/findById/"+parentId+".dhtml",
		        success: function (msg) {
		        	layer.closeAll('page');
		        	count=0;
		        	$("#selectType").val(msg.typeName);
		        	$("#typeCode").val(msg.code);
		         	$("#typeId").val(msg.id);
		                       }
		              })
	 } else {
			$.ajax({
		        type: "get",
		        dataType: "json",
		        url: "${pageContext.request.contextPath}/productType/childLevel/"+parentId+".dhtml",
		        success: function (msg) {
		       			 	var jsonData = eval(msg);
				        	$.each(jsonData.child, function(index, jsonOne) {
				        		 str+="<div><tr id =productName><a onclick=dispaly_child_sort("+jsonOne.id+")>"+jsonOne.typeName+"</a></tr></div>";}	)	;
				        	layer_open(str);
				        	count++;
		                       }
		              })
	 }
};
</script>
</body>
</html>