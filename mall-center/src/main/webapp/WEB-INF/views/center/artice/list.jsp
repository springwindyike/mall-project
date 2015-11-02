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
<title>系统管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 系统日志 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
  <div class="text-c"> 日期范围：
    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})" id="logmin" class="input-text Wdate" style="width:120px;">
    -
    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d'})" id="logmax" class="input-text Wdate" style="width:120px;">
    <input type="text" name="" id="" placeholder="日志名称" style="width:250px" class="input-text"><button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜日志</button>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
  <table class="table table-border table-bordered table-bg table-hover table-sort">
    <thead>
      <tr class="text-c">
        <th width="25"><input type="checkbox" name="" value=""></th>
        <th width="80">ID</th>
        <th width="100">类型</th>
        <th>内容</th>
        <th width="17%">用户名</th>
        <th width="120">客户端IP</th>
        <th width="120">时间</th>
        <th width="70">操作</th>
      </tr>
    </thead>
  </table>
  <div id="pageNav" class="pageNav"></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script> 

<script type="text/javascript">
var targetTable;
var url = "${pageContext.request.contextPath}/artice/allArtice.dhtml";
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
                      { "mDataProp": "type" },
                      { "mDataProp": "content" },
                      { "mDataProp": "name" },
                      { "mDataProp": "ip" },
                      { "mDataProp": "time" },
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
                "targets" : 7,
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
/*  function brand_edit(title, url, id, w, h) {
    layer_show(title, url, w, h);
}  */
</script>
</body>
</html>