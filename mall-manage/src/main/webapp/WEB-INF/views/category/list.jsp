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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>产品分类</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 产品分类 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
	<tr>
		<td width="200" class="va-t"><ul id="treeDemo" class="ztree"></ul></td>
<td class="va-t"><IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=390px SRC="add.dhtml"></IFRAME></td>	</tr>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/zTree/v3/js/jquery.ztree.excheck-3.5.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/zTree/v3/js/jquery.ztree.exedit-3.5.js"></script> 
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
			pIdKey: "parentId",
			rootPId: ""
		}
	},
	edit: {
		enable: true
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			window.location='add.dhtml?level='+treeNode.level+'&id='+treeNode.id+'&code='+treeNode.code;
			if (treeNode.isParent) {
				return false;
			} else {
				demoIframe.attr("src",treeNode.file + ".html");
				return true;
			}
		}
	},beforeDrag: beforeDrag
};

function beforeDrag(treeId, treeNodes) {
	return false;
};
function setEdit() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	remove = $("#remove").attr("checked"),
	rename = $("#rename").attr("checked"),
	removeTitle = $.trim($("#removeTitle").get(0).value),
	renameTitle = $.trim($("#renameTitle").get(0).value);
	zTree.setting.edit.showRemoveBtn = remove;
	zTree.setting.edit.showRenameBtn = rename;
	zTree.setting.edit.removeTitle = removeTitle;
	zTree.setting.edit.renameTitle = renameTitle;
	showCode2(['setting.edit.showRemoveBtn = ' + remove, 'setting.edit.showRenameBtn = ' + rename,
		'setting.edit.removeTitle = "' + removeTitle +'"', 'setting.edit.renameTitle = "' + renameTitle + '"']);
};
var code;
function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}
function showCode2(str) {
	var code = $("#code");
	code.empty();
	for (var i=0, l=str.length; i<l; i++) {
		code.append("<li>"+str[i]+"</li>");
	}
}
$(document).ready(function(){
	$.ajax({
        type: "get",
        dataType: "json",
        url: "${pageContext.request.contextPath}/category/allType.dhtml",
        success: function (msg) {
       var zNodes = msg;
     	var t = $("#treeDemo");
    	t = $.fn.zTree.init(t, setting, zNodes);
    	demoIframe = $("#testIframe");
    	demoIframe.bind("load", loadReady);
    	var zTree = $.fn.zTree.getZTreeObj("tree");
    	setEdit();
		$("#remove").bind("change", setEdit);
		$("#rename").bind("change", setEdit);
		$("#removeTitle").bind("propertychange", setEdit)
		.bind("input", setEdit);
		$("#renameTitle").bind("propertychange", setEdit)
		.bind("input", setEdit);
                       }
              });
});
function loadReady() {
	var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
	htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
	maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
	h = demoIframe.height() >= maxH ? minH:maxH ;
	if (h < 530) h = 530;
	demoIframe.height(h);
}
</script>
</body>
</html>