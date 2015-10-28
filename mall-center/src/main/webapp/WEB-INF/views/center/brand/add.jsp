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
		<title>品牌添加</title>
		<meta name="keywords" content="享买开放平台">
		<meta name="description" content="享买开放平台">
</head>

<body>
<div class="pd-20">
  <form action="${pageContext.request.contextPath}/brand/update.dhtml" method="post" class="form form-horizontal" id="form-brand-update">
  <div> <input type="hidden" class="input-text" value="${returnDTO.id}" placeholder="" id="id" name="id" readonly="readonly" </div>
    <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>名称：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="${returnDTO.name}" placeholder="" id="name" name="name"
               style="border-style:none;">
      </div>
      <div class="col-4"></div>
    </div>

    <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>品牌logo：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="${returnDTO.logoUrl}" placeholder="" id="logoUrl" name="logoUrl" datatype="*2-16" >
      </div>
      <div class="col-4"></div>
    </div>
 <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>国家：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="${returnDTO.country}" placeholder="" id="country" name="country" datatype="*2-16" >
      </div>
      <div class="col-4"></div>
    </div>
    <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>省份：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="${returnDTO.province}" placeholder="" id="province" name="province"">
      </div>
      <div class="col-4"></div>
    </div>
 <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>城市：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="${returnDTO.city}" placeholder="" id="city" name="city"">
      </div>
      <div class="col-4"></div>
    </div>
 <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>区域：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="${returnDTO.district}" placeholder="" id="district" name="district"">
      </div>
      <div class="col-4"></div>
    </div>
<div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>详细：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="${returnDTO.detail}" placeholder="" id="detail" name="detail"">
      </div>
      <div class="col-4"></div>
    </div>
    <div class="row cl">
      <div class="col-9 col-offset-3">
        <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/area.js"></script>
<script type="text/javascript">

/* 省市县选择 */
 $(function(){

    add_select(0);

    $('body').on('change', '#area select', function() {
        var $me = $(this);
        var $next = $me.next();
        /**
         * 如果下一级已经是当前所选地区的子地区，则不进行处理
         */
        if ($me.val() == $next.data('pid')) {
            return;
        }
        $me.nextAll().remove();
        //add_select($me.val());.attr("id"));
        add_select($me.find("option:selected").attr("id"));
    });

    function add_select(pid) {
        var area_names = area['name'+pid];
        if (!area_names) {
            return false;
        }
        var area_codes = area['code'+pid];
        var $select = $('<select class="select" size="1" datatype="*" nullmsg="请选择所在城市！" style="width:98px">');
        $select.attr('name', 'city');
        $select.data('pid', pid);
        if (area_codes[0] != -1) {
            area_names.unshift('请选择');
            area_codes.unshift(-1);
        }
        for (var idx in area_codes) {
            var $option = $('<option>');
            if(area_codes[idx] == -1){
            		$option.attr('value', "");
            }else{
            	$option.attr('value', area_names[idx]);
            					}
            $option.attr('id', area_codes[idx]);
            $option.text(area_names[idx]);
            $select.append($option);
        }
        var length = $("#area select").length;
        if(length < 3){
        $('#area').append($select);
        }
    };
});

//验证
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").Validform({
		tiptype:2,
		ajaxPost:true,
		callback:function(data){
			if(data.success==true){
				setTimeout(function(){
					//$.Hidemsg(); 公用方法关闭信息提示框
					$.Showmsg("注册成功！");
				},2000);
				setTimeout(function(){
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index); 
				},5000);
			}
			if(data.success==false){
				setTimeout(function(){
					$.Showmsg("注册失败！");
				},2000);
			}

		}
	});
});

</script>
</body>
</html>