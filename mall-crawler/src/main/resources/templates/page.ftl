<#assign ctx=request.contextPath />
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<#include "layout/head.ftl">
<#include "layout/jqgrid.ftl">
</head>

<body>

<#include "layout/navbar.ftl">

<div class="container-fluid">
    <div class="row">
    <#import "sidebar.ftl" as sidebar>
    <@sidebar.sidebar></@sidebar.sidebar>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Page Fetch Urls</h1>

            <div>
                <table id="jqGrid"></table>
                <div id="jqGridPager"></div>
            </div>
        </div>
    </div>
</div>

<#include "layout/foot.ftl">
<script type="text/javascript">
    var colNames = ['操作', 'ID', '站点', '商品名称', 'LINK', '抓取时间'];
    var colModel = [
        {
            fixed: true,
            formatter: 'actions'
        }
        , {name: 'id', hidden: true}
        , {name: 'fetchSite', fixed: true}
        , {
            name: 'name', formatter: function (cellvalue, options, rowObject) {
                return '<a href="' + rowObject.link + '", target="_blank">' + rowObject.name + '</a>';
            }
        }
        , {name: 'link', hidden: true}
        , {name: 'createTime', fixed: true}
    ];

    $(document).ready(function () {
        $('#jqGrid').jqGrid({
            url: ctx + '/fetch/pages.json'
            , editurl: ctx + '/fetch/oper'
            , datatype: 'json'
            , mtype: 'GET'
            , autowidth: true
            , height: 'auto'
            , colNames: colNames
            , colModel: colModel
            , pager: "#jqGridPager"
            , toppager: true
            , sortname: 'id'
            , sortorder: 'asc'
            , rowNum: 30
            , rowList: [10, 20, 30, 40, 50]
            , rownumbers: true
            , viewrecords: true
        }).navGrid('#jqGridPager', {
            cloneToTop: true,
            editfunc: function (rowid) {
                console.log('editfunc id', rowid);
                $('#jqGrid').jqGrid('editGridRow', rowid, {
                    closeAfterEdit: true
                });
            }
        });

        $('#jqGrid').navSeparatorAdd('#jqGridPager').navButtonAdd('#jqGridPager', {
            caption: "fetch"
            , onClickButton: function () {
                var rowid = $('#jqGrid').jqGrid('getGridParam', "selrow");
                waitingDialog.show('抓取中...');
                $.post(ctx + '/fetch/url', {id: rowid}, function (response) {
                    waitingDialog.hide();
                    $('#jqGrid').trigger('reloadGrid');
                }, 'json');
            }
            , position: "last"
        });
    });
</script>
</body>
</html>
