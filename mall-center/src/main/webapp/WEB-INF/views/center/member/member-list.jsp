<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
          type="text/css"/>
    <title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span
        class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r mr-20"
                                              style="line-height:1.6em;margin-top:3px
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-member-search">
        <div class="text-c"> 日期范围：
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin"
                   class="input-text Wdate" style="width:120px;">
            -
            <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax"
               class="input-text Wdate" style="width:120px;">

            <input type="text" class="input-text" style="width:250px" placeholder="输入会员名称、电话、账号" id="searchCondition" name="">
            <button type="submit" onclick="searchMember()" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户
            </button>
        </div>
    </form>
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:" onclick="datadel()"
                                                               class="btn btn-danger radius"><i class="Hui-iconfont">
        &#xe6e2;</i> 批量删除</a> <a href="javascript:"
                                 onclick="member_add('添加用户','${pageContext.request.contextPath}/member/addMemberPage.dhtml','','510')"
                                 class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span>
        <span class="r">共有：</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="80">ID</th>
                <th width="100">用户名</th>
                <th width="40">性别</th>
                <th width="90">手机</th>
                <th width="130">加入时间</th>
                <th width="70">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>

                <tr class="text-c">
                    <td>account</td>
                    <td><u style="cursor:pointer" class="text-primary"
                           onclick="member_show('','${pageContext.request.contextPath}/member/findByAccount/xxx.dhtml','10001','360','400')">${memberDetailDTO.name}</u></td>
                    <td></td>
                    <td></td>
                    <td></td>

                </tr>

            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script>
<script type="text/javascript">
    $(function () {
        $('.table-sort').dataTable({
//            "aaSorting": [[1, "desc"]],//默认第几个排序
//            "bStateSave": true,//状态保存
//            "aoColumnDefs": [
//                //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
//                {"orderable": false, "aTargets": [0, 8, 9]}// 制定列不参与排序
//            ]

            "bProcessing": true,
            "bServerSide": true,
            "bStateSave": false,
            "aLengthMenu":[[2, 5, 15, 30], [2, 5, 15, 30]],
            "sAjaxSource": "${pageContext.request.contextPath}/member/findByChannelId.dhtml",
            "sAjaxDataProp":"content",
            "aoColumns": [
                { "mDataProp": "account" },
                { "mDataProp": "name" },
                { "mDataProp": "sex" },
                { "mDataProp": "mobile" },
                { "mDataProp": "createTime" },

            ],
        });
        $('.table-sort tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    });
    /*用户-添加*/
    function member_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }
    /*用户-查看*/
    function member_show(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }
    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
            $(obj).remove();
            layer.msg('已停用!', {icon: 5, time: 1000});
        });
    }

    /*用户-启用*/
    function member_start(obj, id) {
        layer.confirm('确认要启用吗？', function (index) {
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
            $(obj).remove();
            layer.msg('已启用!', {icon: 6, time: 1000});
        });
    }
    /*用户-编辑*/
    function member_edit(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }
    /*密码-修改*/
    function change_password(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }
    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }
    /*根据条件查询*/
    function searchMember(){
        var searchCondition = $("#searchCondition").val();
        var url = '${pageContext.request.contextPath}'+'/member/findBySearchCondition/'+searchCondition+'.dhtml';
        $("#form-member-search").attr("action",url);
        $("#form-member-search").submit();
    }
</script>
</body>
</html>
