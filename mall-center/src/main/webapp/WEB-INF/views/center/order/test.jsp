<%--
  Created by IntelliJ IDEA.
  User: ZhangZhaoxin
  Date: 2015/9/14
  Time: 17:33
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/shopnc/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/shopnc/seller_center.css" rel="stylesheet" type="text/css" />
<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>『享买』开放平台v1.0</title>
		<meta name="keywords" content="享买开放平台">
		<meta name="description" content="享买开放平台">
</head>

<body class="pos-r">
<div >
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div class="text-c"> 
			<table  style="width:auto;" border="0">
  <tr>
	<td>
	  <select name="category" style="width:100px; margin:0 10px;" class="input-text">
		  <option value ="0" selected>全部分类</option>
		  <option>手机</option>
		  <option>相机</option>
	  </select>
	</td>
	<td>
	  <select name="brand" style="width:100px;margin:0 10px;" class="input-text">
		  <option value ="0" selected>全部品牌</option>
		  <option>享买</option>
		  <option>锋果</option>
	  </select>
	</td>
    <td><input type="text" name="" id="searchCondition" placeholder=" 请输入关键字、订单号" style="width:250px" class="input-text"></td>

    <td><button name="" id="" class="btn btn-success" type="submit" onclick="searchOrder();"><i class="Hui-iconfont">&#xe665;</i> 搜订单</button></td>
  </tr>
</table>


		</div>
		<!-- <div class="cl pd-5 bg-1 bk-gray mt-20"> --><!--  <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> </span>  --><!-- <span class="r">共有数据：<strong>54</strong> 条</span> </div> -->
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<!-- <th width="40"><input name="" type="checkbox" value=""></th> -->
						<th width="110">订单号</th>
						<th width="70">商品图片</th>
						<th width="120">商品名称</th>
<!-- 						<th>商品来源</th>
						<th width="110">联系方式</th> -->
						<th width="90">单价（元）</th>
						<th width="50">数量</th>
						<th width="88">购买账号</th>
						<th width="80">收货人</th>
						<th>顾客留言</th>
						<th width="90">付款（元）</th>
						<th width="80">生成时间</th>
						<th width="80">订单状态</th>
						<!-- <th width="70">操作</th> -->
					</tr>
				</thead>
				<tbody>
					<!-- <tr class="text-c va-m"> -->
						<!-- <td rowspan="2"><input name="" type="checkbox" value=""></td> -->
<%-- 						<td>20150825000002</td>
						
						<td><a onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a></td>
						<td class="text-l"><a style="text-decoration:none" onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><b class="text-success">圣象</b> 哥本哈根橡木地板KS8373</a></td>
						<td>小明</td>
						<td>要大一号的，发顺丰。</td>
						<td>121.1元</td>
						<td>3</td>
						<td>2015-08-25 19:22:17</td>
						<td>热的方</td>
						<td><span class="price">356.0</span> 元</td>
						<td class="td-status"><span class="label label-success radius">已发布</span></td>
						<td class="td-manage"> <a style="text-decoration:none" class="ml-5" onClick="product_edit('订单编辑','product-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a><!--  <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a> --></td>
					</tr> --%>
<%-- 					<tr class="text-c va-m">
						<td><a onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a></td>
						<td class="text-l"><a style="text-decoration:none" onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><b class="text-success">圣象</b> 哥本哈根橡木地板KS8373</a></td>
						<td>小明</td>
						<td>要大一号的，发顺丰。</td>
						<td>121.1元</td>
						<td>3</td>
					</tr> --%>
					
				</tbody>
			</table>
		</div>
		<table class="ncsc-default-table order">
			<thead>
			<tr>
				<th class="w10"></th>
				<th colspan="2">商品</th>
				<th class="w100">单价（元）</th>
				<th class="w40">数量</th>
				<th class="w110">买家</th>
				<th class="w120">订单金额</th>
				<th class="w100">交易状态</th>
				<th class="w150">交易操作</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td colspan="20" class="sep-row"></td>
			</tr>
			<tr>
				<th colspan="20"><span class="ml10">订单编号：<em>7000000000000501</em>
        </span> <span>下单时间：<em class="goods-time">2015-11-05 10:04:10</em></span>
					<span class="fr mr5"> <a href="index.php?act=store_order_print&order_id=5" class="ncsc-btn-mini" target="_blank" title="打印发货单"/><i class="icon-print"></i>打印发货单</a></span>
				</th>
			</tr>
			<tr>
				<td class="bdl"></td>
				<td class="w70"><div class="ncsc-goods-thumb"><a href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100011" target="_blank"><img src="http://10.100.100.3/data/upload/shop/store/goods/1/1_0354717f6ctb1f_1x___0-item_pic_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.3/data/upload/shop/store/goods/1/1_0354717f6ctb1f_1x___0-item_pic_240.jpg>')" onMouseOut="toolTip()"/></a></div></td>
				<td class="tl"><dl class="goods-name">
					<dt><a target="_blank" href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100011">简佰格菱格小香风小号贝壳包女包包2015夏新款潮流斜挎包手提包女</a></dt>
					<dd>
					</dd>
				</dl></td>
				<td>249.00</td>
				<td>1</td>

				<!-- S 合并TD -->
				<td class="bdl" rowspan="1"><div class="buyer">qazwc1          <p member_id="4">
				</p>
					<div class="buyer-info"> <em></em>
						<div class="con">
							<h3><i></i><span>联系信息</span></h3>
							<dl>
								<dt>姓名：</dt>
								<dd>test001</dd>
							</dl>
							<dl>
								<dt>电话：</dt>
								<dd>18011231231</dd>
							</dl>
							<dl>
								<dt>地址：</dt>
								<dd>山西省	长治市	武乡县 test02</dd>
							</dl>
						</div>
					</div>
				</div></td>
				<td class="bdl" rowspan="1"><p class="ncsc-order-amount">249.00</p>
					<p class="goods-freight">
						（免运费）                  </p>
					<p class="goods-pay" title="支付方式：货到付款">货到付款</p></td>
				<td class="bdl bdr" rowspan="1"><p><span style="color:#F30">待发货</span>                  </p>

					<!-- 订单查看 -->
					<p><a href="index.php?act=store_order&op=show_order&order_id=5" target="_blank">订单详情</a></p>

					<!-- 物流跟踪 -->
					<p>
					</p>


				</td>

				<!-- 取消订单 -->
				<td class="bdl bdr" rowspan="1">
					<p><a href="javascript:void(0)" class="ncsc-btn ncsc-btn-red mt5" nc_type="dialog" uri="index.php?act=store_order&op=change_state&state_type=order_cancel&order_sn=7000000000000501&order_id=5" dialog_title="取消订单" dialog_id="seller_order_cancel_order" dialog_width="400" id="order5_action_cancel" /><i class="icon-remove-circle"></i>取消订单</a></p>

					<!-- 修改运费 -->
					<!-- 修改价格 -->
					<p><a href="javascript:void(0)" class="ncsc-btn-mini ncsc-btn-green mt10" uri="index.php?act=store_order&op=change_state&state_type=spay_price&order_sn=7000000000000501&order_id=5" dialog_width="480" dialog_title="调整费用" nc_type="dialog"  dialog_id="seller_order_adjust_fee" id="order5_action_adjust_fee" /><i class="icon-pencil"></i>修改价格</a></p>

					<!-- 发货 -->
					<p><a class="ncsc-btn ncsc-btn-green mt10" href="index.php?act=store_deliver&op=send&order_id=5"/><i class="icon-truck"></i>设置发货</a></p>

					<!-- 锁定 -->
				</td>

				<!-- E 合并TD -->
			</tr>

			<!-- S 赠品列表 -->
			<!-- E 赠品列表 -->

			<tbody>
			<tr>
				<td colspan="20" class="sep-row"></td>
			</tr>
			<tr>
				<th colspan="20"><span class="ml10">订单编号：<em>7000000000000401</em>
        </span> <span>下单时间：<em class="goods-time">2015-11-03 13:42:43</em></span>
					<span class="fr mr5"> <a href="index.php?act=store_order_print&order_id=4" class="ncsc-btn-mini" target="_blank" title="打印发货单"/><i class="icon-print"></i>打印发货单</a></span>
				</th>
			</tr>
			<tr>
				<td class="bdl"></td>
				<td class="w70"><div class="ncsc-goods-thumb"><a href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100010" target="_blank"><img src="http://10.100.100.3/data/upload/shop/store/goods/1/1_0422f5b159t1yusfx___0-item_pic_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.3/data/upload/shop/store/goods/1/1_0422f5b159t1yusfx___0-item_pic_240.jpg>')" onMouseOut="toolTip()"/></a></div></td>
				<td class="tl"><dl class="goods-name">
					<dt><a target="_blank" href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100010">老人头OL菱格纹时尚女包 欧美女士包包2014新款牛皮手提包女式包</a></dt>
					<dd>
					</dd>
				</dl></td>
				<td>720.00</td>
				<td>1</td>

				<!-- S 合并TD -->
				<td class="bdl" rowspan="2"><div class="buyer">qazwc1          <p member_id="4">
				</p>
					<div class="buyer-info"> <em></em>
						<div class="con">
							<h3><i></i><span>联系信息</span></h3>
							<dl>
								<dt>姓名：</dt>
								<dd>test001</dd>
							</dl>
							<dl>
								<dt>电话：</dt>
								<dd>18011231231</dd>
							</dl>
							<dl>
								<dt>地址：</dt>
								<dd>山西省	长治市	武乡县 test02</dd>
							</dl>
						</div>
					</div>
				</div></td>
				<td class="bdl" rowspan="2"><p class="ncsc-order-amount">969.00</p>
					<p class="goods-freight">
						（免运费）                  </p>
					<p class="goods-pay" title="支付方式：在线付款">在线付款</p></td>
				<td class="bdl bdr" rowspan="2"><p><span style="color:#36C">待付款</span>                  </p>

					<!-- 订单查看 -->
					<p><a href="index.php?act=store_order&op=show_order&order_id=4" target="_blank">订单详情</a></p>

					<!-- 物流跟踪 -->
					<p>
					</p>


				</td>

				<!-- 取消订单 -->
				<td class="bdl bdr" rowspan="2">
					<p><a href="javascript:void(0)" class="ncsc-btn ncsc-btn-red mt5" nc_type="dialog" uri="index.php?act=store_order&op=change_state&state_type=order_cancel&order_sn=7000000000000401&order_id=4" dialog_title="取消订单" dialog_id="seller_order_cancel_order" dialog_width="400" id="order4_action_cancel" /><i class="icon-remove-circle"></i>取消订单</a></p>

					<!-- 修改运费 -->
					<!-- 修改价格 -->
					<p><a href="javascript:void(0)" class="ncsc-btn-mini ncsc-btn-green mt10" uri="index.php?act=store_order&op=change_state&state_type=spay_price&order_sn=7000000000000401&order_id=4" dialog_width="480" dialog_title="调整费用" nc_type="dialog"  dialog_id="seller_order_adjust_fee" id="order4_action_adjust_fee" /><i class="icon-pencil"></i>修改价格</a></p>

					<!-- 发货 -->

					<!-- 锁定 -->
				</td>

				<!-- E 合并TD -->
			</tr>

			<!-- S 赠品列表 -->
			<!-- E 赠品列表 -->

			<tr>
				<td class="bdl"></td>
				<td class="w70"><div class="ncsc-goods-thumb"><a href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100011" target="_blank"><img src="http://10.100.100.3/data/upload/shop/store/goods/1/1_0354717f6ctb1f_1x___0-item_pic_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.3/data/upload/shop/store/goods/1/1_0354717f6ctb1f_1x___0-item_pic_240.jpg>')" onMouseOut="toolTip()"/></a></div></td>
				<td class="tl"><dl class="goods-name">
					<dt><a target="_blank" href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100011">简佰格菱格小香风小号贝壳包女包包2015夏新款潮流斜挎包手提包女</a></dt>
					<dd>
					</dd>
				</dl></td>
				<td>249.00</td>
				<td>1</td>

				<!-- S 合并TD -->
				<!-- E 合并TD -->
			</tr>

			<!-- S 赠品列表 -->
			<!-- E 赠品列表 -->

			<tbody>
			<tr>
				<td colspan="20" class="sep-row"></td>
			</tr>
			<tr>
				<th colspan="20"><span class="ml10">订单编号：<em>7000000000000301</em>
        </span> <span>下单时间：<em class="goods-time">2015-11-03 13:38:36</em></span>
					<span class="fr mr5"> <a href="index.php?act=store_order_print&order_id=3" class="ncsc-btn-mini" target="_blank" title="打印发货单"/><i class="icon-print"></i>打印发货单</a></span>
				</th>
			</tr>
			<tr>
				<td class="bdl"></td>
				<td class="w70"><div class="ncsc-goods-thumb"><a href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100013" target="_blank"><img src="http://10.100.100.3/data/upload/shop/store/goods/1/1_0369392d5ftb1jwyx___0-item_pic_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.3/data/upload/shop/store/goods/1/1_0369392d5ftb1jwyx___0-item_pic_240.jpg>')" onMouseOut="toolTip()"/></a></div></td>
				<td class="tl"><dl class="goods-name">
					<dt><a target="_blank" href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100013">doodoo时尚女包手提单肩杀手包斜挎女士大包包2015夏新款潮女春款</a></dt>
					<dd>
					</dd>
				</dl></td>
				<td>588.00</td>
				<td>1</td>

				<!-- S 合并TD -->
				<td class="bdl" rowspan="2"><div class="buyer">qazwc1          <p member_id="4">
				</p>
					<div class="buyer-info"> <em></em>
						<div class="con">
							<h3><i></i><span>联系信息</span></h3>
							<dl>
								<dt>姓名：</dt>
								<dd>test001</dd>
							</dl>
							<dl>
								<dt>电话：</dt>
								<dd>18011231231</dd>
							</dl>
							<dl>
								<dt>地址：</dt>
								<dd>山西省	长治市	武乡县 test02</dd>
							</dl>
						</div>
					</div>
				</div></td>
				<td class="bdl" rowspan="2"><p class="ncsc-order-amount">1556.00</p>
					<p class="goods-freight">
						（免运费）                  </p>
					<p class="goods-pay" title="支付方式：货到付款">货到付款</p></td>
				<td class="bdl bdr" rowspan="2"><p><span style="color:#999">已取消</span>                  </p>

					<!-- 订单查看 -->
					<p><a href="index.php?act=store_order&op=show_order&order_id=3" target="_blank">订单详情</a></p>

					<!-- 物流跟踪 -->
					<p>
					</p>


				</td>

				<!-- 取消订单 -->
				<td class="bdl bdr" rowspan="2">

					<!-- 修改运费 -->
					<!-- 修改价格 -->

					<!-- 发货 -->

					<!-- 锁定 -->
				</td>

				<!-- E 合并TD -->
			</tr>

			<!-- S 赠品列表 -->
			<!-- E 赠品列表 -->

			<tr>
				<td class="bdl"></td>
				<td class="w70"><div class="ncsc-goods-thumb"><a href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100014" target="_blank"><img src="http://10.100.100.3/data/upload/shop/store/goods/1/1_03869dae40tb1a21x___0-item_pic_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.3/data/upload/shop/store/goods/1/1_03869dae40tb1a21x___0-item_pic_240.jpg>')" onMouseOut="toolTip()"/></a></div></td>
				<td class="tl"><dl class="goods-name">
					<dt><a target="_blank" href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=100014">朱尔菱格牛皮小包单肩女包新款时尚女士包包斜跨欧美潮公文包女小</a></dt>
					<dd>
					</dd>
				</dl></td>
				<td>968.00</td>
				<td>1</td>

				<!-- S 合并TD -->
				<!-- E 合并TD -->
			</tr>

			<!-- S 赠品列表 -->
			<!-- E 赠品列表 -->

			<tbody>
			<tr>
				<td colspan="20" class="sep-row"></td>
			</tr>
			<tr>
				<th colspan="20"><span class="ml10">订单编号：<em>7000000000000201</em>
        </span> <span>下单时间：<em class="goods-time">2015-07-05 13:02:30</em></span>
					<span class="fr mr5"> <a href="index.php?act=store_order_print&order_id=2" class="ncsc-btn-mini" target="_blank" title="打印发货单"/><i class="icon-print"></i>打印发货单</a></span>
				</th>
			</tr>
			<tr>
				<td class="bdl"></td>
				<td class="w70"><div class="ncsc-goods-thumb"><a href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=46" target="_blank"><img src="http://10.100.100.3/data/upload/shop/store/goods/1/1_04418240378724556_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.3/data/upload/shop/store/goods/1/1_04418240378724556_240.jpg>')" onMouseOut="toolTip()"/></a></div></td>
				<td class="tl"><dl class="goods-name">
					<dt><a target="_blank" href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=46">春装 披肩式 超短款 针织 衫开衫 女装 青鸟 绿色</a></dt>
					<dd>
					</dd>
				</dl></td>
				<td>129.00</td>
				<td>1</td>

				<!-- S 合并TD -->
				<td class="bdl" rowspan="1"><div class="buyer">uctest          <p member_id="2">
					<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1169293220&site=qq&menu=yes" title="QQ: 1169293220"><img border="0" src="http://wpa.qq.com/pa?p=2:1169293220:52" style=" vertical-align: middle;"/></a>
					<a target="_blank" href="http://amos.im.alisoft.com/msg.aw?v=2&uid=yekai58&site=cntaobao&s=2&charset=UTF-8" ><img border="0" src="http://amos.im.alisoft.com/online.aw?v=2&uid=yekai58&site=cntaobao&s=2&charset=UTF-8" alt="Wang Wang" style=" vertical-align: middle;" /></a>
				</p>
					<div class="buyer-info"> <em></em>
						<div class="con">
							<h3><i></i><span>联系信息</span></h3>
							<dl>
								<dt>姓名：</dt>
								<dd>李小明</dd>
							</dl>
							<dl>
								<dt>电话：</dt>
								<dd>18022156458,076088855456</dd>
							</dl>
							<dl>
								<dt>地址：</dt>
								<dd>广东省	中山市	中山市 西区邮局A808号</dd>
							</dl>
						</div>
					</div>
				</div></td>
				<td class="bdl" rowspan="1"><p class="ncsc-order-amount">129.00</p>
					<p class="goods-freight">
						（免运费）                  </p>
					<p class="goods-pay" title="支付方式：网银在线">网银在线</p></td>
				<td class="bdl bdr" rowspan="1"><p><span style="color:#F30">待发货</span>                  </p>

					<!-- 订单查看 -->
					<p><a href="index.php?act=store_order&op=show_order&order_id=2" target="_blank">订单详情</a></p>

					<!-- 物流跟踪 -->
					<p>
					</p>


				</td>

				<!-- 取消订单 -->
				<td class="bdl bdr" rowspan="1">

					<!-- 修改运费 -->
					<!-- 修改价格 -->

					<!-- 发货 -->
					<p><a class="ncsc-btn ncsc-btn-green mt10" href="index.php?act=store_deliver&op=send&order_id=2"/><i class="icon-truck"></i>设置发货</a></p>

					<!-- 锁定 -->
				</td>

				<!-- E 合并TD -->
			</tr>

			<!-- S 赠品列表 -->
			<!-- E 赠品列表 -->

			<tbody>
			<tr>
				<td colspan="20" class="sep-row"></td>
			</tr>
			<tr>
				<th colspan="20"><span class="ml10">订单编号：<em>7000000000000101</em>
        </span> <span>下单时间：<em class="goods-time">2015-07-05 13:00:34</em></span>
					<span class="fr mr5"> <a href="index.php?act=store_order_print&order_id=1" class="ncsc-btn-mini" target="_blank" title="打印发货单"/><i class="icon-print"></i>打印发货单</a></span>
				</th>
			</tr>
			<tr>
				<td class="bdl"></td>
				<td class="w70"><div class="ncsc-goods-thumb"><a href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=46" target="_blank"><img src="http://10.100.100.3/data/upload/shop/store/goods/1/1_04418240378724556_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.3/data/upload/shop/store/goods/1/1_04418240378724556_240.jpg>')" onMouseOut="toolTip()"/></a></div></td>
				<td class="tl"><dl class="goods-name">
					<dt><a target="_blank" href="http://10.100.100.3/shop/index.php?act=goods&op=index&goods_id=46">春装 披肩式 超短款 针织 衫开衫 女装 青鸟 绿色</a></dt>
					<dd>
					</dd>
				</dl></td>
				<td>129.00</td>
				<td>1</td>

				<!-- S 合并TD -->
				<td class="bdl" rowspan="1"><div class="buyer">uctest          <p member_id="2">
					<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1169293220&site=qq&menu=yes" title="QQ: 1169293220"><img border="0" src="http://wpa.qq.com/pa?p=2:1169293220:52" style=" vertical-align: middle;"/></a>
					<a target="_blank" href="http://amos.im.alisoft.com/msg.aw?v=2&uid=yekai58&site=cntaobao&s=2&charset=UTF-8" ><img border="0" src="http://amos.im.alisoft.com/online.aw?v=2&uid=yekai58&site=cntaobao&s=2&charset=UTF-8" alt="Wang Wang" style=" vertical-align: middle;" /></a>
				</p>
					<div class="buyer-info"> <em></em>
						<div class="con">
							<h3><i></i><span>联系信息</span></h3>
							<dl>
								<dt>姓名：</dt>
								<dd>李小明</dd>
							</dl>
							<dl>
								<dt>电话：</dt>
								<dd>18022156458,076088855456</dd>
							</dl>
							<dl>
								<dt>地址：</dt>
								<dd>广东省	中山市	中山市 西区邮局A808号</dd>
							</dl>
						</div>
					</div>
				</div></td>
				<td class="bdl" rowspan="1"><p class="ncsc-order-amount">129.00</p>
					<p class="goods-freight">
						（免运费）                  </p>
					<p class="goods-pay" title="支付方式：站内余额支付">站内余额支付</p></td>
				<td class="bdl bdr" rowspan="1"><p><span style="color:#F30">待收货</span>                  </p>

					<!-- 订单查看 -->
					<p><a href="index.php?act=store_order&op=show_order&order_id=1" target="_blank">订单详情</a></p>

					<!-- 物流跟踪 -->
					<p>
					</p>


				</td>

				<!-- 取消订单 -->
				<td class="bdl bdr" rowspan="1">

					<!-- 修改运费 -->
					<!-- 修改价格 -->

					<!-- 发货 -->

					<!-- 锁定 -->
				</td>

				<!-- E 合并TD -->
			</tr>

			<!-- S 赠品列表 -->
			<!-- E 赠品列表 -->

			</tbody>
			<tfoot>
			<tr>
				<td colspan="20"><div class="pagination"><ul><li><span>首页</span></li><li><span>上一页</span></li><li><span class="currentpage">1</span></li><li><span>下一页</span></li><li><span>末页</span></li></ul></div></td>
			</tr>
			</tfoot>
		</table>

	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script> 
<script type="text/javascript">	
var targetTable;
var url = "${pageContext.request.contextPath}/order/findByChannelId.dhtml";
$(function () {
	 targetTable = $('.table-sort').DataTable({
		"oLanguage": {
			"sInfoEmpty": "没有数据",
			"sZeroRecords": "没有数据",
			"sEmptyTable":"没有数据"
		},
		"searching": false,
		"ordering":  false,
		"bProcessing": true,
		"bServerSide": true,
		"bStateSave": false,
		"aLengthMenu":[[2, 5, 15, 30], [2, 5, 15, 30]],
		"ajax": {
			url:url,
			"dataSrc": "content"
		},
		"aoColumns": [
			{ "mDataProp": "orderId" },//订单号
			{ "mDataProp": null },//商品图片
			{ "mDataProp": null },//商品名称
			{ "mDataProp": null },//单价（元）
			{ "mDataProp": null },//数量
			{ "mDataProp": "createBy" },//购买账号
			{ "mDataProp": "recipients" },//收货人
			{ "mDataProp": "note" },//顾客留言
			{ "mDataProp": "totalPrice" },//付款（元）
			{ "mDataProp": "createTime" },//生成时间
			{ "mDataProp": null }  //订单状态
			//{ "mDataProp": null }//操作
		],
			
			"createdRow" : function(row, mDataProp, dataIndex){
			   $(row).addClass('text-c');
			},
			
			"columnDefs" : [
				{
					"targets" : 1 ,
					"render" : function(mDataProp, type, full) {
						return '<a onClick="product_show(\'哥本哈根橡木地板\',\'product-show.html\',\'10001\')" href="javascript:;"><img width="60" class="product-thumb" src="${pageContext.request.contextPath}/resources/images/admin-login-bg.jpg"></a>';
					}
				},
				{
					"targets" : 2 ,
					"render" : function(mDataProp, type, full) {
						var itemHtml = "";
						for (var i = 0; i < mDataProp.items.length; i++) 
							{
							itemHtml = mDataProp.items[i].productName;
							}
						return '<a style="text-decoration:none" onClick="product_show(\''+itemHtml+'\',\'product-show.html\',\'10001\')" href="javascript:;">'+itemHtml+'</a>';
					}
				},
				{
					"targets" : 3 ,
					"render" : function(mDataProp, type, full) {
						var itemHtml = "";
						for (var i = 0; i < mDataProp.items.length; i++) 
							{
							itemHtml = mDataProp.items[i].productPrice;
							}
						return itemHtml;
						}
				},
				{
					"targets" : 4 ,
					"render" : function(mDataProp, type, full) {
						var itemHtml = "";
						for (var i = 0; i < mDataProp.items.length; i++) 
							{
							itemHtml = mDataProp.items[i].amount;
							}
						return itemHtml;
					}
				},
				{
					"targets" : 10 ,
					"render" : function(mDataProp, type, full) {
						var itemHtml = mDataProp.stateValue;
						if(itemHtml == '已取消' || itemHtml == '待审核' ){
							return '<span class="outspan"><span class="label label-defaunt radius">'+itemHtml+'</span></span>';
						}
						return '<span class="outspan"><span class="label label-success radius">'+itemHtml+'</span></span>'; 
					 }
				}/* ,
				{
					"targets" : 11 ,
					"orderable":false,
					"aTargets":[11],
					"render" : function(mDataProp, type, full) {
						return '<td class="td-manage"> <a style="text-decoration:none" class="ml-5" onClick="product_edit(\'订单编辑\',\'product-add.html\',\'10001\')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a></td>';
					}
				} */
			]
			});
	
	$('.table-sort tbody').on('click', 'tr', function () {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		}
		else {
			$('table tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
});	
/*根据条件查询*/
function searchOrder(){
    var searchCondition = $("#searchCondition").val();
    url = '${pageContext.request.contextPath}'+'/order/findBySearchCondition/'+searchCondition+'.dhtml';
    targetTable.ajax.url(url).load();

}

/*图片-添加*/
/* function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
} */
/*图片-查看详情*/
/* function product_show(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
} */
/*图片-审核*/
/* function product_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过'], 
		shade: false
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
} */
/*图片-下架  在页面已删除 可改为订但相关*/
/* function product_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
} */

/*图片-发布 在页面已删除 可改为订但相关*/
/* function product_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
} */
/*图片-申请上线 在页面已删除 可改为订但相关*/
/* function product_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
} */
/*图片-编辑*/
/* function product_edit(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
} */
/*图片-删除*/
/* function product_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
} */
</script>
</body>