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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link href="resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="resources/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />

<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>找回密码</title>
		<meta name="keywords" content="享买开放平台">
		<meta name="description" content="享买开放平台">
</head>

<body>
<div class="pd-20">
  <form action="" method="post" class="form form-horizontal" id="form-find-password">
    <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>手机：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="" placeholder="" id="member-tel" name="member-tel"  datatype="m" nullmsg="手机不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3">验证码：</label>
      <div class="formControls col-5"> 
        <input class="input-text" type="text" name="verifyCode" id="verifyCode" datatype="*" nullmsg="请填写验证码！" style="width:200px">
        <a href="javascript:void();" class="btn btn-primary radius">发送验证码</a>
      </div>
      <div class="col-4"> </div>
    </div>

    <div class="row cl">
      <div class="col-9 col-offset-3">
        <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;立刻找回&nbsp;&nbsp;">
      </div>
    </div>
  </form>
</div>
</div>
<script type="text/javascript" src="resources/scripts/jquery.min.js"></script> 
<script type="text/javascript" src="resources/scripts/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="resources/scripts/Validform.min.js"></script>
<script type="text/javascript" src="resources/scripts/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="resources/scripts/H-ui.admin.js"></script>
<script type="text/javascript">
$(function(){
	$("#form-find-password").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	});
});

</script>
</body>
</html>