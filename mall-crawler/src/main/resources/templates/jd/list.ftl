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
            <div>
                <form class="form-horizontal" id="mysearch">
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">商品名称</label>
                        <input type="text" class="form-control" id="search_name" placeholder="商品名称" name="search_name">
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">价格区间</label>
                        <input type="number" class="form-control" id="search_price_min" placeholder="价格下限"
                               name="search_price_min" value=""> - <input
                            type="number" class="form-control" id="search_price_max" placeholder="价格上限"
                            name="search_price_max" value="">
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">上架时间</label>
                        <input type="datetime" class="form-control" id="search_update_time_min"
                               name="search_update_time_min" placeholder="开始时间" value=""> -
                        <input type="datetime" class="form-control" id="search_update_time_max"
                               name="search_update_time_max" placeholder="结束时间" value="">
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">库存</label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="search_stock_y" name="search_stock" value="有货"> 有货
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="search_stock_n" name="search_stock" value="无货"> 无货
                        </label>
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-2 control-label">自营</label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="search_self_y" name="search_self" value="true"> 是
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="search_self_n" name="search_self" value="false"> 否
                        </label>
                    </div>
                    <div class="form-inline form-group">
                        <label class="col-sm-4 control-label"></label>
                        <button type="submit" class="btn btn-primary form-control">查询</button>
                        <button type="reset" class="btn btn-primary form-control">重置</button>

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
                    label: '更新时间', name: 'updateTime', width: 70, align: 'center', search: false, hidden: true
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
            pager: '#jqGridPager',
            toppager: true,
            subGrid: true,
            subGridRowExpanded: function (pid, id) {
                $.getJSON(ctx + '/jd/page/' + id, function (result) {
                    var p = result;
                    var html = $('#' + pid);

                    var row = '<p>' + p.tag + '</p>';

                    row += '<p>相册：</p><div class="row">';
                    var photo = p.photo;
                    for (var index = 0; index < photo.length; index++) {
                        var img = photo[index];//height="200" width="200"
                        row += '<div class="col-xs-6 col-md-3"><a href="' + img + '" class="thumbnail"><img src="' + img + '" alt="' + p.name + '"></a></div>';
                    }
                    row += '</div>';

                    row += '<p>图文：</p><div class="row">';
                    var imgs = p.introImgs;
                    for (var index = 0; index < imgs.length; index++) {
                        var img = imgs[index];
                        row += '<div class="col-xs-6 col-md-3"><a href="' + img + '" class="thumbnail"><img src="' + img + '" alt="' + p.name + '"></a></div>';
                    }
                    row += '</div>';
                    html.append(row);
                });
                //$('#'+pid).append('<p class="text">hello world</p>');
            }
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
            format: "yyyy-mm-dd HH:ii:ss",
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        });
        $('#search_update_time_max').datetimepicker({
            format: "yyyy-mm-dd HH:ii:ss",
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        });

        $('#mysearch').submit(function () {
            console.log('查询', $('#mysearch').serialize());
            $('#jqGrid').jqGrid('setGridParam', {
                url: ctx + '/jd/lists/search.json?' + $('#mysearch').serialize()
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