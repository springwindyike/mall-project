<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<#include "../layout/jqgrid.ftl">
    <link href="${springMacroRequestContext.contextPath}/css/dashboard.css" rel="stylesheet">
    <script src="${springMacroRequestContext.contextPath}/js/bootstrap-waitingfor.js"></script>
    <script>
        var ctx = '${springMacroRequestContext.contextPath}';
    </script>
</head>
<body>
<#include "../layout/navbar.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="${springMacroRequestContext.contextPath}">Overview</a></li>
                <li class="active"><a href="${springMacroRequestContext.contextPath}/jd/category">JD Category <span
                        class="sr-only">(current)</span></a></li>
                <li><a href="${springMacroRequestContext.contextPath}/jd/list">JD List</a></li>
                <li><a href="${springMacroRequestContext.contextPath}/jd/page">JD Page</a></li>
            </ul>

        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">JD Category</h1>
            <!--
            <div id="mysearch">
                <form class="form-inline">
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" placeholder="京东类目">
                    </div>
                    <button type="submit" class="btn btn-default">搜</button>
                </form>
            </div>-->
            <table id="jqGrid"></table>
            <div id="jqGridPager"></div>
        </div>
    </div>
</div>
<#include "../layout/foot.ftl">
<script type="text/javascript">
    $(document).ready(function () {
        console.log('hello category');
        $('#jqGrid').jqGrid({
            altRows: true,
            autowidth: true,
            height: 'auto',
            url: ctx + '/jd/category.json',
            datatype: 'json',
            colModel: [
                {label: 'ID', name: 'id', hidden: true},
                {
                    label: '京东类目', name: 'name', search: true, formatter: function (cellvalue, options, rowObject) {
                    if (rowObject.link == null) {
                        return rowObject.name;
                    } else {
                        return '<a href="' + rowObject.link + '" target="_blank">' + rowObject.name + '</a>';
                    }
                }
                }, {
                    label: '链接', name: 'link', search: false, formatter: function (cellvalue, options, rowObject) {
                        if (rowObject.link == null) {
                            return '';
                        }
                        return '<a href="' + rowObject.link + '", target="_blank">' + rowObject.link + '</a>';
                    }
                },
                {
                    label: '操作',
                    align: 'center',
                    width: 30,
                    search: false,
                    formatter: function (cellvalue, options, rowObject) {
                        if (rowObject.link == null || rowObject.id == 1) {
                            return '<button class="btn btn-primary" disabled="disabled">抓</button>';
                        }
                        return '<button class="btn btn-primary" onclick="fetch(' + rowObject.id + ')">抓</button>';
                    }
                }
            ],
            viewrecords: true,
            rowList: [10, 20, 30, 50, 100],
            rownumbers: true,
            rowNum: 50,
            pager: '#jqGridPager'
        });

        $('#jqGrid').navGrid('#jqGridPager', {
            edit: false,
            add: false,
            del: false,
            search: false,
            refresh: true,
            view: true,
            position: "left",
            cloneToTop: false
        });

        $('#jqGrid').jqGrid('filterToolbar', {
            autosearch: true,
            searchOnEnter: true
        });

    });
    function fetch(id) {
        waitingDialog.show('抓取中...');

        $.post(ctx + '/jd/fetch/category/' + id, function (result) {
            waitingDialog.hide();
        }, 'json');
    }
</script>
</body>
</html>