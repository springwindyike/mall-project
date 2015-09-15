<!DOCTYPE html>
<html lang="en">
<head>
<#include "layout/head.ftl">
</head>

<body>

<#include "layout/navbar.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="${springMacroRequestContext.contextPath}">Overview <span class="sr-only">(current)</span></a>
                </li>
                <li><a href="${springMacroRequestContext.contextPath}/jd/category">JD Category</a></li>
                <li><a href="${springMacroRequestContext.contextPath}/jd/list">JD List</a></li>
                <li><a href="${springMacroRequestContext.contextPath}/jd/page">JD Page</a></li>
            </ul>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Dashboard</h1>

            <div class="table-responsive">
                <table id="table_id" class="table table-striped">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Name</th>
                        <th>Link</th>
                    </tr>
                    </thead>
                </table>
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
