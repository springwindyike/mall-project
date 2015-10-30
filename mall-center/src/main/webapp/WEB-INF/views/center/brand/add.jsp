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
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>品牌管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 品牌管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
<div> 
<div id="result"></div>
<img id="uploadImage" src="http://www.firefox.com.cn/favicon.ico">
<input type="file" id="myBlogImage" name="file"/>
<input type="button" value="上传图片" onclick="ajaxFileUpload()"/>
</div>
	<div class="text-c">
		<form class="Huiform" target="_self" id="add_brand" enctype="multipart/form-data">
			<input type="text" placeholder="分类名称" value="" id="name" name="name" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
				<input type="text" placeholder="国家" value="" id="country" name="country" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
				<input type="text" placeholder="省份" value="" id="province" name="province" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
				<input type="text" placeholder="城市" value="" id="city" name="city" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
			<input type="text" placeholder="区域" value="" id="district" name="district" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
				<input type="text" placeholder="具体描述"  value="" id="detail" name="detail" class="input-text" style="width:120px">
			<span class="btn-upload form-group">
				<input type="hidden" placeholder=""  value="" id="logo" name="logo" class="input-text" style="width:120px">
	</span><button type="button" class="btn btn-success" id="" name="" onClick="picture_colume_add();"><i class="Hui-iconfont">&#xe600;</i> 添加</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/ajaxfileupload.js"></script> 
<script type="text/javascript">

function picture_colume_add(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/brand/add.dhtml",
        data:$('#add_brand').serialize(),// 你的formid
	});
	//$("#add_brand").submit();
} 
function ajaxFileUpload(){
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
    //执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
       url:"${pageContext.request.contextPath}/brand/uploadPic.dhtml",
        secureuri:false,                       //是否启用安全提交,默认为false
        fileElementId:'myBlogImage',           //文件选择框的id属性
        dataType:'text',                       //服务器返回的格式,可以是json或xml等
        success:function(data, status){        //服务器响应成功时的处理函数
            data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
            data = data.replace("</PRE>", '');
            data = data.replace("<pre>", '');
            data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
            if(data.substring(0, 1) == 0){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
                $("img[id='uploadImage']").attr("src", data.substring(2));
                $('#result').html("图片上传成功<br/>");
                $('#logo').val(data.substring(2));
            }else{
                $('#result').html('图片上传失败，请重试！！');
            }
        },
        error:function(data, status, e){ //服务器响应失败时的处理函数
            $('#result').html('图片上传失败，请重试！！');
        }
    });
}
</script>
</body>
</html>