<html>
<head>
<#include "../layout/head.ftl">
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
                <li><a href="${springMacroRequestContext.contextPath}/jd/list">JD List</a></li>
                <li class="active"><a href="${springMacroRequestContext.contextPath}/jd/page">JD Page <span
                        class="sr-only">(current)</span></a></li>
            </ul>

        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">JD Page</h1>

            <form class="form-horizontal" id="searchForm">
                <div class="input-group">
                    <input type="text" class="form-control" name="searchInput" id="searchInput"
                           placeholder="Search for...">

                    <div class="input-group-btn">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">操作 <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li><a href="#" id="searchJD">搜京东</a></li>
                            <li><a href="#" id="fetchPage">抓商品</a></li>
                        </ul>
                    </div>
                    <!-- /btn-group -->
                </div>
                <!-- /input-group -->
            </form>

            <div id="searchResult">
            </div>
        </div>
    </div>
</div>
<#include "../layout/foot.ftl">
<script>
    $(document).ready(function () {
        console.log('hello page');

        $('#searchJD').click(function () {
            waitingDialog.show('抓取中...');
            var value = $('#searchInput').val();
            $.post(ctx + '/jd/search', {searchParam: value, action: 'search'}, function (result) {
                waitingDialog.hide();
                console.log(result);

                if (result.success) {
                    var searchResult = $('#searchResult');
                    searchResult.empty();
                    var data = result.searchResult;
                    for (var index = 0; index < data.length; index++) {
                        var p = data[index];
                        var row = '<div class="panel panel-default">';
                        row += '<div class="panel-heading">';
                        row += '<h3 class="panel-title">' + (index + 1) + '. ' + p.name + '</h3>';
                        row += '</div>';
                        row += '<div class="panel-body">';
                        row += '<div class="media">';
                        row += '<div class="media-left">';
                        row += '<a href="#">';
                        row += '<img class="media-object" src="' + p.img + '" alt="' + p.name + '">';
                        row += '</a>';
                        row += '</div>';
                        row += '<div class="media-body">';
                        row += '<h4 class="media-heading">' + p.price + '</h4>';
                        row += '<p><a class="btn btn-primary" href="' + p.link + '" target="_blank">源地址</a></p>';
                        row += '<p><button id="fetch' + index + '" class="btn btn-danger" href="' + p.link + '" onclick="fetch(' + index + ')">抓一把</<button></p>';
                        row += '</div>';
                        row += '</div>';
                        row += '</div>';
                        row += '</div>';
                        searchResult.append(row);
                    }
                }

            }, 'json');
        });

        $('#fetchPage').click(function () {
            waitingDialog.show('抓取中...');
            var value = $('#searchInput').val();
            $.post(ctx + '/jd/search', {searchParam: value, action: 'fetch'}, function (result) {
                waitingDialog.hide();
                console.log(result);
                if (!result.success) {
                    alert('error');
                }
            }, 'json');
        });


    });

    function fetch(id) {
        waitingDialog.show('抓取中...');
        console.log('fetch btn id: ', id);
        var url = 'http:' + $('#fetch' + id).attr('href');
        $.getJSON(ctx + '/jd/fetch/page', {url: url}, function (result) {
            waitingDialog.hide();
            if (!result.success) {
                alert('fetch fail');
            }
        });
    }
</script>
</body>
</html>