<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8">
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport"
        content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link href="${pageContext.request.contextPath}/resources/shopnc/base.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/seller_center.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/perfect-scrollbar.min.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/skin_0.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css" id="cssfile2" />
  <link href="${pageContext.request.contextPath}/resources/shopnc/jquery.qtip.min.css" rel="stylesheet" type="text/css" />
  <title>订单详情</title>
</head>
<body>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>

  <input type="hidden" name="form_submit" value="ok" />
  <div class="ncsc-flow-layout">
    <div class="ncsc-flow-container">
      <div class="title">
        <h3>退货退款服务</h3>
      </div>
      <div id="saleRefundReturn">
        <div class="ncsc-form-default">
          <h3>买家退货退款申请</h3>
          <dl>
            <dt>退货退款编号：</dt>
            <dd>${orderRefundDTO.refundId} </dd>
          </dl>
          <dl>
            <dt>申请人（买家）：</dt>
            <dd>${orderRefundDTO.buyerName}</dd>
          </dl>
          <dl>
            <dt>退货原因：</dt>
            <dd> ${orderRefundDTO.buyerMessage} </dd>
          </dl>
          <dl>
            <dt>退款金额：</dt>
            <dd>&yen;${orderRefundDTO.refundAmount}</dd>
          </dl>
          <dl>
            <dt>退货数量：</dt>
            <dd>${orderRefundDTO.productNumber}</dd>
          </dl>
          <dl>
            <dt>凭证上传：</dt>
            <dd><img width="60px" alt="" src="${orderRefundDTO.refundPicture}" />
            </dd>
          </dl>
          <form id="post_form" method="post" action="${pageContext.request.contextPath}/order/go2Confirm/${orderRefundDTO.refundId}.dhtml">
            <input type="hidden" name="form_submit" value="ok" />
            <h3>商家处理意见</h3>
            <dl>
              <dt><i class="required">*</i>是否同意：</dt>
              <dd>
                <div>
                  <label class="mr20">
                    <input type="radio" class="vm" name="seller_state" value="2" />
                    同意</label>
                  <c:if test="${orderRefundDTO.refundType == 2}"><label>
                    <input name="return_type" class="vm" type="checkbox" value="1" />
                    弃货</label>
                    <p class="hint">如果选择弃货，买家将不用退回原商品，提交后直接由管理员确认退款。</p></c:if>
                </div>
                <div class="mt10">
                  <label>
                    <input type="radio" class="vm" name="seller_state" value="3" />
                    拒绝</label>
                </div>
                <span class="error"></span>
              </dd>
            </dl>
            <dl>
              <dt><i class="required">*</i>备注信息：</dt>
              <dd>
                <textarea name="seller_message" rows="2" class="textarea w300"></textarea>
                <span class="error"></span>
                <p class="hint"> 如是同意退货，请及时关注买家的发货情况，并进行收货（发货5天后可以选择未收到，超过7天不处理按弃货处理）。<br>
                </p>
              </dd>
            </dl>
            <div class="bottom">
              <label class="submit-border">
                <a class="submit" id="confirm_button">确定</a>
              </label>
              <label class="submit-border">
                <a href="javascript:history.go(-1);" class="submit">返回列表</a>
              </label>
            </div>
          </form>
        </div>
      </div>
    </div>

    <div class="ncsc-flow-item">
      <div class="title">相关商品交易信息</div>
      <div class="item-goods">
        <dl>
          <dt>
          <div class="ncsc-goods-thumb-mini"><a target="_blank" href="http://10.100.100.15/shop/index.php?act=goods&op=index&goods_id=100019">
            <img src="http://10.100.100.15/data/upload/shop/store/goods/1/1_04074b7572tb1iyux___2-item_pic_60.jpg" onMouseOver="toolTip('<img src=http://10.100.100.15/data/upload/shop/store/goods/1/1_04074b7572tb1iyux___2-item_pic_240.jpg>')" onMouseOut="toolTip()" /></a></div>
          </dt>
          <dd><a target="_blank" href="http://10.100.100.15/shop/index.php?act=goods&op=index&goods_id=100019">女包2015夏款潮手提包真皮女包大包大容量单肩包女士包包时尚女包</a>
            &yen;424.00 * 1 <font color="#AAA">(数量)</font>
            <span></span>
          </dd>
        </dl>
      </div>
      <div class="item-order">
        <dl>
          <dt>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</dt>
          <dd>（免运费）</dd>
        </dl>
        <dl>
          <dt>订单总额：</dt>
          <dd><strong>&yen;424.00                </strong> </dd>
        </dl>
        <dl class="line">
          <dt>订单编号：</dt>
          <dd><a target="_blank" href="index.php?act=store_order&op=show_order&order_id=7">7000000000000701</a> <a href="javascript:void(0);" class="a">更多<i class="icon-angle-down"></i>
            <div class="more"> <span class="arrow"></span>
              <ul>
                <li>付款单号：<span>910500659767840004</span></li>
                <li>支付方式：<span>财付通</span></li>
                <li>下单时间：<span>2015-11-12 16:09:27</span></li>
                <li>付款时间：<span>2015-11-12 00:00:00</span></li>
                <li>发货时间：<span>2015-11-12 16:09:57</span></li>
                <li>完成时间：<span>2015-11-12 16:10:07</span></li>
              </ul>
            </div>
          </a> </dd>
        </dl>
        <dl>
          <dt>物流单号：</dt>
          <dd>dfsadfsafd <a href="javascript:void(0);" class="a">顺丰快递</a></dd>
        </dl>
        <dl class="line">
          <dt>收&nbsp;货&nbsp;人：</dt>
          <dd>adfsadfs<a href="javascript:void(0);" class="a">更多<i class="icon-angle-down"></i>
            <div class="more"><span class="arrow"></span>
              <ul>
                <li>收货地址：<span>山西省	阳泉市	矿区 dfsafdsafd</span></li>
                <li>联系电话：<span>18888888888</span></li>
              </ul>
            </div>
          </a>
            <div><span member_id="4"></span>
            </div>
          </dd>
        </dl>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/jquery.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/jquery.validation.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/waypoints.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/jquery.ui.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/member.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/perfect-scrollbar.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/jquery.qtip.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/shopnc/jquery.nyroModal/js/compare.js" charset="utf-8"></script>
<script type="text/javascript">
  $(function(){
    $("#confirm_button").click(function(){
      $("#post_form").submit();
    });
    $('#post_form').validate({
      errorPlacement: function(error, element){
        error.appendTo(element.parentsUntil('dl').find('span.error'));
      },
      rules : {
        seller_state : {
          required   : true
        },
        seller_message : {
          required   : true
        }
      },
      messages : {
        seller_state  : {
          required  : '请选择是否同意'
        },
        seller_message  : {
          required   : '备注信息不能为空'
        }
      }
    });
  });
</script>
</body>
</html>
