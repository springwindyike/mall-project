<!DOCTYPE html>
<html lang="zh_CN">
<head>

<#include "layout/head.ftl">
</head>
<body>
<#include "layout/navbar.ftl">

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>爬虫管理 测试界面</h1>
    </div>

    <div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active" id="homeTab"><a href="#home" aria-controls="home" role="tab"
                                                                   data-toggle="tab">Category</a>
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
                            <button class="btn btn-primary ladda-button" data-style="room-in"
                                    data-title="${category.link}"><span class="ladda-label">抓取</span></button>
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
                        <th>PRICE</th>
                        <th>OP</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list products.content as product>
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.code}</td>
                        <td>
                            <#if (product.updateTime)??>
                            ${product.updateTime}
                        </#if>
                        </td>
                        <td><a href="${product.link}" target="_blank">${product.name}</a></td>
                        <td>${product.price!""}</td>
                        <td>
                            <button class="btn btn-danger ladda-button" data-title="${product.link}"><span
                                    class="ladda-label">抓</span></button>
                            <a class="btn btn-primary" href="product/${product.id}" target="_self">看</a>
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
            var link = btn.attr('data-title');
            console.log(link);
            /*
            e.preventDefault();
            var l = Ladda.create(this);
            l.start();
            $.post("your-url", {data: data}, function (response) {
                console.log(response);
            }, "json").always(function () {
                l.stop();
            });
            */
            var l = Ladda.create(this);
            l.start();
            $.post('fetchList', {link: link}, function (response) {
                console.log(response);
            }, 'json').always(function () {
                l.stop();
            });
        });

        $('div#profile>table>tbody>tr>td>button:first-child').click(function () {
            var btn = $(this);
            var link = btn.attr('data-title');

            var l = Ladda.create(this);
            l.start();
            $.post('fetchPage', {link: link}, function (response) {
                console.log(response);
            }, 'json').always(function () {
                l.stop();
            });
        });


    });
</script>
</body>
</html>