<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<#include "../layout/jqgrid.ftl">
    <link href="${springMacroRequestContext.contextPath}/css/dashboard.css" rel="stylesheet">
    <script src="${springMacroRequestContext.contextPath}/js/bootstrap-waitingfor.js"></script>

    <link rel="stylesheet"
          href="${springMacroRequestContext.contextPath}/datetimepicker/css/bootstrap-datetimepicker.css">
    <script src="${springMacroRequestContext.contextPath}/datetimepicker/js/bootstrap-datetimepicker.js"></script>

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
                <li><a href="${springMacroRequestContext.contextPath}/jd/category">JD Category</a></li>
                <li class="active"><a href="${springMacroRequestContext.contextPath}/jd/list">JD List <span
                        class="sr-only">(current)</span></a></li>
                <li><a href="${springMacroRequestContext.contextPath}/jd/page">JD Page</a></li>
            </ul>

        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Dashboard JD Product List</h1>
            <!---->
            <div id="mysearch">
                <form class="form-horizontal">
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">商品名称</label>
                        <input type="text" class="form-control" id="search_name" placeholder="商品名称">
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">价格区间</label>
                        <input type="number" class="form-control" id="search_price_min" placeholder="价格下限"> - <input
                            type="number" class="form-control" id="search_price_max" placeholder="价格上限">
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">上架时间</label>
                        <input type="datetime" class="form-control" id="search_update_time_min" placeholder="开始时间"> -
                        <input type="datetime" class="form-control" id="search_update_time_max" placeholder="结束时间">
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">库存</label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="inlineCheckbox1" value="option1"> 有货
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="inlineCheckbox2" value="option2"> 无货
                        </label>
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">自营</label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="inlineCheckbox1" value="option1"> 是
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="inlineCheckbox2" value="option2"> 否
                        </label>
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-4 control-label"></label>
                        <button type="submit" class="btn btn-primary form-control">搜</button>
                    </div>
                </form>
            </div>
            <table id="jqGrid"></table>
            <div id="jqGridPager"></div>
        </div>
    </div>
</div>
<#include "../layout/foot.ftl">
<script>
    $(document).ready(function () {
        console.log('hello list');

        var grid = $('#jqGrid').jqGrid({
            altRows: true,
            autowidth: true,
            height: 'auto',
            url: ctx + '/jd/lists.json',
            datatype: 'json',
            colModel: [
                {label: 'ID', name: 'id', hidden: true},
                {
                    label: '商品名称', name: 'name', search: false, formatter: function (cell, options, row) {
                    if (row.link != null) {
                        return '<a href="' + row.link + '" target="_blank">' + row.name + '</a>';
                    } else {
                        return row.name;
                    }
                }
                },
                {label: '价格', name: 'price', width: 40, align: 'center', search: false},
                {
                    label: '库存', name: 'stock', width: 20, align: 'center', formatter: function (cell, options, row) {
                    if (row.stock == '无货') {
                        return '<font color="red">' + row.stock + '</font>';
                    } else if (row.stock == null) {
                        return '';
                    }
                    return row.stock;
                }
                },
                {
                    label: '自营', name: 'self', width: 20, align: 'center', formatter: function (cell, options, row) {
                    return row.self ? '是' : '否';
                }
                },
                {
                    label: '更新时间', name: 'updateTime', width: 70, align: 'center', search: false
                }, {
                    label: '上架时间', name: 'jdDatetime', width: 70, align: 'center', search: false
                },
                {
                    label: '操作', search: false, width: 25, formatter: function (cellvalue, options, row) {
                    return '<button class="btn btn-primary" onclick="fetch(' + row.id + ')">抓</button>';
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

        $('#search_update_time_min').datetimepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        });
        $('#search_update_time_max').datetimepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        });

        $('#mysearch > form > button').click(function () {
            var name = $('#search_name').val();
            var priceMin = $('#search_price_min').val();
            var priceMax = $('#search_price_max').val();
            var start = $('#search_update_time_min').val();
            var end = $('#search_update_time_max').val();
            console.log('查询');
            $('#jqGrid').jqGrid('setGridParam', {
                //TODO 查询URL要单独重写一个
            }).trigger("reloadGrid");
            return false;
        });
    });

    function fetch(id) {
        waitingDialog.show('抓取中...');

        $.post(ctx + '/jd/fetch/page/' + id, function (result) {
            waitingDialog.hide();
            $('#jqGrid').jqGrid().trigger("reloadGrid");

            if (!result.success) {
                alert('抓取失败，请重新抓一次');
            }
        }, 'json');
    }
</script>
</body>
</html>