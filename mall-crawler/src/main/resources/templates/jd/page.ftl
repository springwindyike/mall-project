<html>
<head>
<#include "../layout/head.ftl">
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


        </div>
    </div>
</div>
<#include "../layout/foot.ftl">
<script>
    $(document).ready(function () {
        console.log('hello page');
    });
</script>
</body>
</html>