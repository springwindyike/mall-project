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
  <form action="${pageContext.request.contextPath}/order/deliver.dhtml" method="post" class="form form-horizontal" id="form-member-deliver">
  		<input name="orderId" type="hidden" value="${orderId }"/>
    <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>请选择物流公司：</label>
      <div class="formControls col-5">
	      	<span class="select-box" id="logistics">
	      	
	        </span> 
        </div>
      <div class="col-4"> </div>
    </div>
			<div class="row cl">
				 <label class="form-label col-3"><span class="c-red">*</span>运单号码：</label>
				 <div class="formControls col-5">
						<input type="text" class="input-text" autocomplete="off" placeholder="" name="expressOrder" id="expressOrder" datatype="*5-30" nullmsg="运单号码不能为空" >
					</div>
					<div class="col-4"> </div>
				</div>
    <div class="row cl">
      <label class="form-label col-3">备注：</label>
      <div class="formControls col-5">
        <textarea name="note" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,100)"></textarea>
        <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <div class="col-9 col-offset-3">
        <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认发货&nbsp;&nbsp;">
      </div>
    </div>
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
		ajaxPost:true,
		callback:function(data){
			if(data.success==true){
				setTimeout(function(){
					//$.Hidemsg(); 公用方法关闭信息提示框
					$.Showmsg("发货成功！");
				},2000);
				setTimeout(function(){
/* 					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);  */
					parent.location.reload();
				},5000);
			}
			if(data.success==false){
				setTimeout(function(){
					$.Showmsg("发货失败！");
				},2000);
			}

		}
	});
});

</script>
</body>
</html>