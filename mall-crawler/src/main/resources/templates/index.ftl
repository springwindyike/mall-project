<!DOCTYPE html>
<html lang="zh_CN">
<head>

<#include "layout/head.ftl">
</head>
<body>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">OpenAPI</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="init" id="initBtn">初始化</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            <#--
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
            aria-expanded="false">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li class="dropdown-header">Nav header</li>
            <li><a href="#">Separated link</a></li>
            <li><a href="#">One more separated link</a></li>
            -->
            </ul>
            </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>爬虫管理 测试界面</h1>

    </div>

    <div>

        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Category</a>
            </li>
            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Product
                List</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="home">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>URL</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list categories.content as category>
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>${category.link}</td>
                        <td>
                            <button class="btn btn-primary" data-link="${category.link}">抓取</button>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                    <tfoot>
                    <nav>
                        <ul class="pager">
                            <li class="previous ${categories.first?string('disabled', '')}"><a
                                    href="/?page=${categories.number+1}"><span aria-hidden="true">&larr;</span>
                                Older</a>
                            </li>
                            共 ${categories.totalPages} 页，共 ${categories.totalElements} 条记录，当前第${categories.number}页
                            <li class="next ${categories.last?string('disabled', '')}"><a
                                    href="?page=${categories.number+1}">Newer <span aria-hidden="true">&rarr;</span></a>
                            </li>
                        </ul>
                    </nav>
                    </tfoot>
                </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="profile">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>CODE</th>
                        <th>TIME</th>
                        <th>NAME</th>
                        <th>OP</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list products.content as product>
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.code}</td>
                        <td>${product.createTime}</td>
                        <td>${product.name}</td>
                        <td>
                            <button class="btn btn-danger">抓</button>
                            <!-- Large modal -->
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#myModal${product.id}">
                                看
                            </button>

                            <!-- Modal -->
                            <div class="modal fade bs-example-modal-lg" id="myModal${product.id}"
                                 tabindex="${product.id}" role="dialog" aria-labelledby="myModalLabel${product.id}">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" id="myModalLabel${product.id}">${product.name}</h4>
                                        </div>
                                        <div class="modal-body">
                                        ${product.name}
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>
                    </tr>
                    </#list>
                    </tbody>
                    <tfoot>
                    <nav>
                        <ul class="pager">
                            <li class="previous ${products.first?string('disabled', '')}"><a
                                    href="/?page2=${products.number+1}"><span aria-hidden="true">&larr;</span> Older</a>
                            </li>
                            共 ${products.totalPages} 页，共 ${products.totalElements} 条记录，当前第${products.number}页
                            <li class="next ${products.last?string('disabled', '')}"><a
                                    href="?page2=${products.number+1}">Newer <span aria-hidden="true">&rarr;</span></a>
                            </li>
                        </ul>
                    </nav>
                    </tfoot>
                </table>


            </div>
        </div>

    </div>

    <div>

    </div>
</div>

<#include "layout/foot.ftl">
<script>
    $(document).ready(function () {

        $('div#home>table>tbody>tr>td>button').click(function () {
            var btn = $(this);
            btn.attr('disabled', 'disabled');
            var link = $(this).attr('data-link');
            console.log(link);
//            $.getJSON('fetch', {link: link}, function (result) {
//                console.log(result);
//
//                btn.removeAttr('disabled');
//            });
        });
    });
</script>
</body>
</html>