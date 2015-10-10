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
            <h1 class="page-header">Dashboard</h1>

            <!-- 仪表盘 -->
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
