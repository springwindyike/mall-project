<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成都熊猫</title>
<script type="text/javascript" src="webapp/resources/shopnc/jquery.nyroModal/js/jquery.js"></script>
<script type="text/javascript" src="webapp/resources/shopnc/jquery.nyroModal/js/jquery.validation.min.js"></script>
<script type="text/javascript" src="webapp/resources/shopnc/jquery.nyroModal/js/admincp.js"></script>
<script type="text/javascript" src="webapp/resources/shopnc/jquery.nyroModal/js/jquery.cookie.js"></script>
<script type="text/javascript" src="webapp/resources/shopnc/jquery.nyroModal/js/common.js" charset="utf-8"></script>
<link href="http://10.100.100.15/admin/templates/default/css/skin_0.css" rel="stylesheet" type="text/css" id="cssfile2" />
<link href="webapp/resources/shopnc/jquery.nyroModal/js/perfect-scrollbar.min.css" rel="stylesheet" type="text/css">
<link href="http://10.100.100.15/admin/templates/default/css/font/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<!--[if IE 7]>
  <link rel="stylesheet" href="http://10.100.100.15/admin/templates/default/css/font/font-awesome/css/font-awesome-ie7.min.css">
<![endif]-->
<script type="text/javascript" src="webapp/resources/shopnc/jquery.nyroModal/js/perfect-scrollbar.min.js"></script>

<script type="text/javascript">
SITEURL = 'http://10.100.100.15/shop';
RESOURCE_SITE_URL = 'http://10.100.100.15/data/resource';
MICROSHOP_SITE_URL = 'http://10.100.100.15/microshop';
CIRCLE_SITE_URL = 'http://10.100.100.15/circle';
ADMIN_TEMPLATES_URL = 'http://10.100.100.15/admin/templates/default';
LOADING_IMAGE = "http://10.100.100.15/admin/templates/default/images/loading.gif";
//换肤
cookie_skin = $.cookie("MyCssSkin");
if (cookie_skin) {
	$('#cssfile2').attr("href","http://10.100.100.15/admin/templates/default/css/"+ cookie_skin +".css");
}
</script>
</head>
<body>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<script type="text/javascript" src="webapp/resources/shopnc/jquery.nyroModal/js/jquery.mousewheel.js"></script>

<div class="page">
  <div class="fixed-bar">
    <div class="item-title">
      <h3>系统信息<!--上次登录的时间：2015-11-18 13:57:53--></h3>
    </div>
  </div>
  <div class="fixed-empty"></div>
  <div class="info-panel">
    <dl class="member" onclick="javascript:window.location.href='${pageContext.request.contextPath}/manageUser/forward2ManageUsreList.dhtml'">
      <dt>
        <div class="ico"><i></i><sub title="会员总数"><span><em id="statistics_member">${memberNum}</em></span></sub></div>
        <h3>会员</h3>
        <h5>新增会员/预存款提现</h5>
      </dt>
      <dd>
        <ul>
          <li class="w50pre normal"><a href="${pageContext.request.contextPath}/member/list.dhtml">本周新增<sub><em id="statistics_week_add_member">${memberThisWeekNum}</em></sub></a></li>
          <li class="w50pre none"><a href="index.php?act=predeposit&op=pd_cash_list">预存款提现<sub><em id="statistics_cashlist">0</em></sub></a></li>
        </ul>
      </dd>
    </dl>
    <dl class="shop">
      <dt>
        <div class="ico"><i></i><sub title="渠道商数"><span><em id="statistics_store">${channelNum}</em></span></sub></div>
        <h3>渠道商</h3>
        <h5>渠道商审核/申请</h5>
      </dt>
      <dd>
        <ul>
          <li class="w25pre normal"><a href="${pageContext.request.contextPath}/channel/listthisweek.dhtml">本周新增<sub><em id="statistics_store_joinin">${channelThisWeekNum}</em></sub></a></li>
          <li class="w33pre none"><a href="index.php?act=store&op=store_bind_class_applay_list&state=0">审核<sub><em id="statistics_store_bind_class_applay">0</em></sub></a></li>
          <li class="w34pre none"><a href="index.php?act=store&op=store_bind_class_applay_list&state=0">申请<sub><em id="statistics_store_bind_class_applay">0</em></sub></a></li>
          <%--<li class="w20pre none"><a href="index.php?act=store&op=reopen_list&re_state=1">续签申请<sub><em id="statistics_store_reopen_applay">0</em></sub></a></li>--%>
          <%--<li class="w20pre none"><a href="index.php?act=store&op=store&store_type=expired">已到期<sub><em id="statistics_store_expired">0</em></sub></a></li>--%>
          <%--<li class="w20pre none"><a href="index.php?act=store&op=store&store_type=expire">即将到期<sub><em id="statistics_store_expire">0</em></sub></a></li>--%>
        </ul>
      </dd>
    </dl>


    <dl class="goods">
      <dt>
        <div class="ico"><i></i><sub title="商品总数"><span><em id="statistics_goods">${goodsNum}</em></span></sub></div>
        <h3>商品</h3>
        <h5>新增商品/品牌申请审核</h5>
      </dt>
      <dd>
        <ul>
          <li class="w25pre normal"><a href="${pageContext.request.contextPath}/product/thisweek.dhtml">本周新增<sub title=""><em id="statistics_week_add_product">${productThisWeekCount}</em></sub></a></li>
          <li class="w25pre none"><a href="http://10.100.100.15/admin/index.php?act=goods&op=goods&type=waitverify&search_verify=10">商品审核<sub><em id="statistics_product_verify">0</em></sub></a></li>
          <li class="w25pre none"><a href="index.php?act=inform&op=inform_list">举报<sub><em id="statistics_inform_list">0</em></sub></a></li>
          <li class="w25pre none"><a href="index.php?act=brand&op=brand_apply">品牌申请<sub><em id="statistics_brand_apply">0</em></sub></a></li>
        </ul>
      </dd>
    </dl>
    <dl class="trade">
      <dt>
        <div class="ico"><i></i><sub title="订单总数"><span><em id="statistics_order">${orderMum}</em></span></sub></div>
        <h3>交易</h3>
        <h5>交易订单及投诉/举报</h5>
      </dt>
      <dd>
        <ul>
          <li class="w18pre none"><a href="index.php?act=refund&op=refund_manage">退款<sub><em id="statistics_refund"></em></sub></a></li>
          <li class="w18pre none"><a href="index.php?act=return&op=return_manage">退货<sub><em id="statistics_return"></em></sub></a></li>
          <li class="w25pre none"><a href="index.php?act=vr_refund&op=refund_manage">虚拟订单退款<sub><em id="statistics_vr_refund"></em></sub></a></li>
          <li class="w18pre none"><a href="index.php?act=complain&op=complain_new_list">投诉<sub><em id="statistics_complain_new_list">0</em></sub></a></li>
          <li class="w20pre none"><a href="index.php?act=complain&op=complain_handle_list">待仲裁<sub><em id="statistics_complain_handle_list">0</em></sub></a></li>
        </ul>
      </dd>
    </dl>
    <dl class="operation">
      <dt>
        <div class="ico"><i></i></div>
        <h3>运营</h3>
        <h5>系统运营类设置及审核</h5>
      </dt>
      <dd>
        <ul>
          <li class="w15pre none"><a href="index.php?act=groupbuy&op=groupbuy_verify_list">抢购<sub><em id="statistics_groupbuy_verify_list">0</em></sub></a></li>
          <li class="w17pre none"><a href="index.php?act=pointorder&op=pointorder_list&porderstate=waitship">积分订单<sub><em id="statistics_points_order">0</em></sub></a></li>
          <li class="w17pre none"><a href="index.php?act=bill&op=show_statis&os_month=&query_store=&bill_state=2">账单审核<sub><em id="statistics_check_billno">0</em></sub></a></li>
          <li class="w17pre none"><a href="index.php?act=bill&op=show_statis&os_month=&query_store=&bill_state=3">账单支付<sub><em id="statistics_pay_billno">0</em></sub></a></li>
          <li class="w17pre none"><a href="http://10.100.100.15/admin/index.php?act=mall_consult&op=index">平台客服<sub><em id="statistics_mall_consult">0</em></sub></a></li>
          <li class="w17pre none"><a href="http://10.100.100.15/admin/index.php?act=delivery&op=index&sign=verify">服务站<sub><em id="statistics_delivery_point">0</em></sub></a></li>
        </ul>
      </dd>
    </dl>

    <dl class="供应商">
      <dt>
      <div class="ico"><i></i><sub title="供应商总数"><span><em id="statistics_supplier">${goodsNum}</em></span></sub></div>
      <h3>供应商</h3>
      <h5>本周新增/供应商申请审核</h5>
      </dt>
      <dd>
        <ul>
          <li class="w25pre normal"><a href="${pageContext.request.contextPath}/product/thisweek.dhtml">本周新增<sub title=""><em id="statistics_week_add_product">${productThisWeekCount}</em></sub></a></li>
          <li class="w33pre none"><a href="http://10.100.100.15/admin/index.php?act=cms_picture&op=cms_picture_list_verify">审核<sub><em id="statistics_cms_picture_verify">0</em></sub></a></li>
          <li class="w34pre none"><a href="http://10.100.100.15/admin/index.php?act=cms_comment&op=comment_manage">申请<sub></sub></a></li>
        </ul>
      </dd>
    </dl>
    <dl class="">
     <dt>
        <div class="ico"><i></i></div>
        <h3>圈子</h3>
        <h5>申请开通/圈内话题及举报</h5>
      </dt>
      <dd>
        <ul>

          <li class="w33pre none"><a href="http://10.100.100.15/admin/index.php?act=circle_manage&op=circle_verify">圈子申请<sub><em id="statistics_circle_verify">0</em></sub></a></li>
          <li class="w33pre none"><a href="http://10.100.100.15/admin/index.php?act=circle_theme&op=theme_list">话题</a></li>
          <li class="w34pre none"><a href="http://10.100.100.15/admin/index.php?act=circle_inform&op=inform_list">举报</a></li>
        </ul>
      </dd>
    </dl>
            <dl class="microshop">
      <dt>
        <div class="ico"><i></i></div>
        <h3>微商城</h3>
        <h5>随心看/个人秀/店铺街</h5>
      </dt>
      <dd>
        <ul>
          <li class="w33pre none"><a href="http://10.100.100.15/admin/index.php?act=microshop&op=goods_manage">随心看</a></li>
          <li class="w33pre none"><a href="http://10.100.100.15/admin/index.php?act=circle_theme&op=theme_list">个人秀</a></li>
          <li class="w34pre none"><a href="http://10.100.100.15/admin/index.php?act=circle_inform&op=inform_list">店铺街</a></li>
        </ul>
      </dd>
    </dl>
        <dl class="system">
      <dt>
        <div class="ico"><i></i></div>
        <h3></h3>
        <div id="system-info">
          <ul>
            <li>多用户商城 <span>20150315</span></li>
            <li><span>2015-11-12</span></li>
            <li><span>WINNT</span></li>
            <li>WEB <span>Apache/2.2.17 (Win32) PHP/5.3.3</span></li>
            <li>PHP <span>5.3.3</span></li>
            <li>MYSQL <span>5.5.27</span></li>
          </ul>
        </div>
      </dt>
      <dd>
        <ul>
          <li class="w50pre none"><a href="#" target="_blank">官方网站<sub></sub></a></li>
          <li class="w50pre none"><a href="#" target="_blank">官方论坛<sub></sub></a></li>
        </ul>
      </dd>
    </dl>
    <div class="clear"></div>
    <div class="system-info"></div>
  </div>
</div>
<script type="text/javascript">
var normal = ['week_add_member','week_add_product'];
var work = ['store_joinin','store_bind_class_applay','store_reopen_applay','store_expired','store_expire','brand_apply','cashlist','groupbuy_verify_list','points_order','complain_new_list','complain_handle_list', 'product_verify','inform_list','refund','return','vr_refund','cms_article_verify','cms_picture_verify','circle_verify','check_billno','pay_billno','mall_consult','delivery_point','offline'];
$(document).ready(function(){
	$.getJSON("index.php?act=dashboard&op=statistics", function(data){
	  $.each(data, function(k,v){
		  $("#statistics_"+k).html(v);
		  if (v!= 0 && $.inArray(k,work) !== -1){
			$("#statistics_"+k).parent().parent().parent().removeClass('none').addClass('high');
		  }else if (v == 0 && $.inArray(k,normal) !== -1){
			$("#statistics_"+k).parent().parent().parent().removeClass('normal').addClass('none');
		  }
	  });
	});
	//自定义滚定条
	$('#system-info').perfectScrollbar();
});
</script>
</body>
</html>
