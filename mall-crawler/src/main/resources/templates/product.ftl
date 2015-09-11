<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <title></title>
<#include "layout/head.ftl">
</head>
<body>
<#include "layout/navbar.ftl">
<div class="container">
    <div class="page-header">
        <h1>爬虫管理 测试界面 页面抓取明细</h1>

    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><a href="${product.link}" target="_blank">${product.name}</a></h3>
        </div>
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">价格</label>

                    <div class="col-sm-10">
                        <p class="form-control-static">${product.price}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">自营</label>

                    <div class="col-sm-10">
                        <p class="form-control-static">${product.self?string('是','否')}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">库存</label>

                    <div class="col-sm-10">
                        <p class="form-control-static">${product.stock}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">标签</label>

                    <div class="col-sm-10">
                        <p class="form-control-static">${product.tag}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">更新时间</label>

                    <div class="col-sm-10">
                        <p class="form-control-static">${product.updateTime}</p>
                    </div>
                </div>
                <div class="form-group">
                    <a class="btn btn-lg btn-primary form-control"
                       href="${springMacroRequestContext.contextPath}">back</a>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "layout/foot.ftl">
<script>
    $(document).ready(function () {

    });
</script>
</body>
</html>