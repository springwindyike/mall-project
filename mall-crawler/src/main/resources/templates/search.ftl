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

    });
</script>
</body>
</html>
