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

            <form class="form-horizontal">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id="searchBtn">Go!</button>
                  </span>
                </div>
                <!-- /input-group -->
            </form>

            <div id="searchContent" class="panel panel-default hidden">
                <div class="panel-heading">
                    <h3 class="panel-title">Panel title</h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">价格</label>

                            <div class="col-sm-10">
                                <p class="form-control-static"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">自营</label>

                            <div class="col-sm-10">
                                <p class="form-control-static"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">库存</label>

                            <div class="col-sm-10">
                                <p class="form-control-static"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">标签</label>

                            <div class="col-sm-10">
                                <p class="form-control-static"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">更新时间</label>

                            <div class="col-sm-10">
                                <p class="form-control-static">2015-09-14 22:44:51</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品介绍</label>

                            <div class="col-sm-10">
                                <p class="form-control-static">key: value</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">相册</label>

                            <div class="col-sm-10">
                                <p class="form-control-static">

                                <div class="row">
                                    <div class="col-xs-6 col-md-3">
                                        <a href="#" class="thumbnail">
                                            <img src="#" alt="">
                                        </a>
                                    </div>
                                </div>
                                </p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">图文</label>

                            <div class="col-sm-10">
                                <p class="form-control-static">

                                <div class="row">
                                    <div class="col-xs-6 col-md-3">
                                        <a href="#" class="thumbnail">
                                            <img src="#" alt="">
                                        </a>
                                    </div>
                                </div>
                                </p>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../layout/foot.ftl">
<script>
    $(document).ready(function () {
        console.log('hello page');
        $('#searchBtn').click(function () {
            $('#searchContent').removeClass('hidden');
            return false;
        });
    });
</script>
</body>
</html>