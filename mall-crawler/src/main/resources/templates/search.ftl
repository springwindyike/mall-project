<#assign ctx=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
<#include "layout/head.ftl">
</head>

<body>

<#include "layout/navbar.ftl">

<div class="container-fluid">
    <div class="row">
    <#import "sidebar.ftl" as sidebar>
    <@sidebar.sidebar></@sidebar.sidebar>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Search</h1>

            <div>
            <#--<form>-->
                <div class="input-group form-group">
                    <span class="input-group-addon" id="fetch-url">http://</span>
                    <input type="text" class="form-control" placeholder="需要抓取的URL" aria-describedby="fetch-url">
                    <span class="input-group-btn"><button class="btn btn-default" type="button">Go!</button></span>
                </div>
            <#--</form>-->
            </div>

            <div>
            <#--<form>-->
                <div class="input-group form-group">
                    <button class="btn btn-primary" id="testA">测试BizApp 1 条数据入口接口</button>
                    <span>   </span>
                    <button class="btn btn-primary" id="testB">测试BizApp 20 条数据入口接口</button>
                    <span>   </span>
                    <button class="btn btn-primary" id="testC">测试BizApp N 条数据入口接口</button>
                </div>
            <#--</form>-->
            </div>

            <div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        Basic panel example
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "layout/foot.ftl">
<script type="text/javascript">
    $(document).ready(function () {
        console.log('OpenAPI Crawler Dashboard');

        $('#testA').click(function () {
            waitingDialog.show('单条数据发送中...');
            $.getJSON(ctx + '/fetch/test', {isSingle: true}, function (response) {
                waitingDialog.hide();
                alert('success: ' + response.success);
            });
        });

        $('#testB').click(function () {
            waitingDialog.show('20条数据发送中...');
            $.getJSON(ctx + '/fetch/test', {isSingle: false}, function (response) {
                waitingDialog.hide();
                alert('success: ' + response.success);
            });
        });

        $('#testC').click(function () {
            waitingDialog.show('N条数据发送中...');
            $.getJSON(ctx + '/fetch/test', {isSingle: false, isAll: true}, function (response) {
                waitingDialog.hide();
                alert('success: ' + response.success);
            });
        });
    });
</script>
</body>
</html>
