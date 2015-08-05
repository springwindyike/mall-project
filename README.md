陶瓷网
===
###目录说明
* eshop-web 陶瓷网购物网站，只包含本工程需要的Controller和页面代码
* eshop-admin 陶瓷网管理后台，只包含本工程需要的Controller和页面代码
* eshop-service 平台公用代码，包括数据库、对外服务接口等

####技术文档
* [Spring Framework Reference Documentation](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/)
* [Spring Data JPA - Reference Documentation](http://docs.spring.io/spring-data/jpa/docs/1.8.1.RELEASE/reference/html/)
* [EasyUI Documentation](http://www.jeasyui.com/documentation/index.php)
* [EasyUI Tutorial](http://www.jeasyui.com/tutorial/index.php)
* [JQuery 速查手册](http://tool.oschina.net/apidocs/apidoc?api=jquery)
* [JQuery API](http://api.jquery.com/)
* [Bootstrap](http://getbootstrap.com/)
* [OSC Git 帮助文档](http://git.oschina.net/oschina/git-osc/wikis/%E5%B8%AE%E5%8A%A9#继续阅读)
* [GIT 官方中文文档](http://git-scm.com/book/zh/v1)
* [安全框架shiro](http://shiro.apache.org/)

* [Google网页代理](https://github.com/sxyx2008/DevArticles/issues/99) 方便搜资料，百度确实太难用了

* [Markdown 语法说明](http://wowubuntu.com/markdown/)

####安装命令
    mvn clean resources:resources antrun:run package -Pdev -DskipTests=true

####初始化数据库
    mvn clean resources:resources antrun:run -Pdev
或者运行eshop-docs下面的refresh-db.sh