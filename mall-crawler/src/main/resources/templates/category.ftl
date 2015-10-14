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
            <h1 class="page-header">Category Fetch Urls</h1>

            <div>
                <table id="jqGrid"></table>
                <div id="jqGridPager"></div>
            </div>
        </div>
    </div>
</div>

<#include "layout/foot.ftl">
<script type="text/javascript">
    var colNames = ['ID', '站点', '类目', 'LINK', '抓取时间'];
    var colModel = [
        {name: 'id', key: true, hidden: true}
        , {
            name: 'fetchSite', editable: true, search: true, stype: 'select', searchoptions: {
                value: ':全部;JD:京东;DANG_DANG:当当;AMAZON:亚马逊'
            }
        }
        , {
            name: 'name', editable: true, formatter: function (cellvalue, options, rowObject) {
                return '<a href="' + rowObject.link + '", target="_blank">' + rowObject.name + '</a>';
            }
        }
        , {
            name: 'link', hidden: true, editable: true, editrules: {
                edithidden: false
            }
        }
        , {name: 'createTime', editable: true}
    ];

    $(document).ready(function () {
        $('#jqGrid').jqGrid({
            url: ctx + '/fetch/urls.json'
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
            , multiselect: true
            , viewrecords: true
        }).navGrid('#jqGridPager', {
            cloneToTop: true,
            edit: false, add: false, del: true, search: true, refresh: true
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

        $('#jqGrid').navSeparatorAdd('#jqGridPager').navButtonAdd('#jqGridPager', {
            caption: "抓取当前页"
            , onClickButton: function () {
                var rowids = $("#jqGrid").jqGrid("getGridParam", "selarrrow");
                console.log(rowids);
                /**/
                waitingDialog.show('抓取中...');
                $.post(ctx + '/fetch/urls', {ids: rowids}, function (response) {
                    waitingDialog.hide();
                    $('#jqGrid').trigger('reloadGrid');
                }, 'json');

            }
            , position: "last"
        });

        $('#jqGrid').jqGrid('filterToolbar', {
            autosearch: true
        });
    });
</script>
</body>
</html>
