<%--
  Created by IntelliJ IDEA.
  User: dongqi
  Date: 15/7/23
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>会员管理</title>
    <%@ include file="/WEB-INF/views/include/easyui.jsp" %>
    <script src="${ctx}/static/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

</head>
<body style="font-family: '微软雅黑'">

<div id="tb" style="padding:5px;height:auto">
    <div>
        <form id="searchFrom" action="">
            <input type="text" name="search_LIKE_account" class="easyui-validatebox"
                   data-options="width:100,prompt: '账号'"/>
            <input type="text" name="search_LIKE_realName" class="easyui-validatebox"
                   data-options="width:100,prompt: '姓名'"/>
            <input type="text" name="search_LIKE_email" class="easyui-validatebox"
                   data-options="width:100,prompt: '邮箱'"/>
            <input type="text" name="search_GTE_createTime" class="easyui-my97" datefmt="yyyy-MM-dd"
                   data-options="width:150,prompt: '开始日期'"/>
            - <input type="text" name="search_LTE_createTime" class="easyui-my97" datefmt="yyyy-MM-dd"
                     data-options="width:150,prompt: '结束日期'"/>
            <span class="toolbar-item dialog-tool-separator"></span>
            <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
        </form>

        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="upd()">修改</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
           onclick="lockOrUnlock('lock')">锁定</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
           onclick="lockOrUnlock('unlock')">解锁</a>
        <%--
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="">密码重置</a>
        --%>
    </div>

</div>
<table id="dg"></table>
<div id="dlg"></div>

</body>

<script type="text/javascript">
    var dg;
    var d;
    $(document).ready(function() {
        var customerGridColumns = [[
            {field: 'id', width: 100, title: 'ID'},
            {field: 'account', width: 100, title: '账号'},
            {field: 'realName', width: 100, title: '姓名'},
            {field: 'gender', width: 100, title: '性别', formatter: function(value, row, index) {
                return value == 0 ? '男' : '女';
            }},
            {field: 'email', width: 100, title: '邮箱'},
            {field: 'mobile', width: 100, title: '电话'},
            {
                field: 'lock', width: 50, title: '锁定',
                formatter: function (value, row, index) {
                    return value ? '是' : '否';
                }
            },
            {
                field: 'createTime', width: 100, title: '注册时间', formatter: function (value, row, index) {
                var date = new Date();
                date.setTime(value);
                date = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
                return date;
            }
            }
        ]];

        dg=$('#dg').datagrid({
            method: "get",
            url:'${ctx}/customer/list',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50 ],
            singleSelect:true,
            columns: customerGridColumns,
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb',
            loadMsg: '努力加载中。。。'
        });


    });
    //创建查询对象并查询
    function cx(){
        var obj=$("#searchFrom").serializeObject();
        dg.datagrid('load',obj);
    }

    //弹窗修改
    function upd() {
        var row = dg.datagrid('getSelected');
        if (rowIsNull(row)) return;
        d = $("#dlg").dialog({
            title: '修改会员信息',
            width: 380,
            height: 340,
            href: '${ctx}/customer/update/' + row.id,
            maximizable: true,
            modal: true,
            buttons: [{
                text: '修改',
                handler: function () {
                    $('#mainform').submit();
                }
            }, {
                text: '取消',
                handler: function () {
                    d.panel('close');
                }
            }]
        });
    }

    //弹窗锁定/解锁
    function lockOrUnlock(op) {
        var row = dg.datagrid('getSelected');
        if (rowIsNull(row)) return;
        var text = (op == 'lock') ? '解锁' : '锁定';
        //console.log('${ctx}/customer/'+op+'/'+row.id);
        d = $("#dlg").dialog({
            title: '会员锁定/解锁',
            width: 380,
            height: 340,
            href: '${ctx}/customer/' + op + '/' + row.id,
            maximizable: true,
            modal: true,
            buttons: [{
                text: text,
                handler: function () {
                    $('#mainform').submit();
                }
            }, {
                text: '取消',
                handler: function () {
                    d.panel('close');
                }
            }]
        });
    }
</script>

</html>
