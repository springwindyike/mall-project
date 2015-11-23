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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

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
  <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 退款列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
  <div class="pd-20">
    <div class="text-c">
      <table  style="width:auto;" border="0">

        <tr class="text-c">
          <td>
            <select name="findBy" style="width:100px; margin:0 10px;" class="input-text" id="findBy">
              <option  value="orderId" selected>订单号</option>
              <option value="refundId">退款号</option>
              <option value="channelName">渠道名称</option>
              <option value="productName">商品名称</option>
              <option value="buyerName">买家名称</option>
            </select>
          </td>
          <td><input type="text" name="" id="detail" placeholder="" style="width:250px" class="input-text"></td>
          <th>下单时间:</th>
          <td>
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:118px;">
            -
            <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:118px;">
          </td>
          <td><button name="" id="" class="btn btn-success" type="submit" onclick="searchOrder();"><i class="Hui-iconfont">&#xe665;</i>搜索</button></td>
        </tr>
      </table>


    </div>
    <div class="mt-20">
      <table class="table table-border table-bordered table-bg table-hover table-sort">
        <thead>
        <tr class="text-c">
          <th width="40">订单编号</th>
          <th width="40">退款编号</th>
          <th width="88">渠道</th>
          <th width="88">商品名称</th>
          <th width="88">买家名称</th>
          <th width="110">申请时间</th>
          <th width="40">商家审核</th>
          <th width="110">商家审核时间</th>
          <th width="40">平台审核</th>
          <th width="40">退款金额</th>
          <th width="40">操作</th>
        </tr>
        </thead>
        <tbody>
        <input id="refundStatus" type="hidden" value="${status}"/>
        </tbody>
      </table>
    </div>
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
  var url = "${pageContext.request.contextPath}/order/getRefundMoney/1.dhtml";
  var refundStatus = $("#refundStatus").val();
  if(refundStatus == "true"){
    alert("退款成功");
  }else if(refundStatus == "false"){
    alert("退款失败，请联系管理");
  }
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
      "aLengthMenu":[[5, 7, 15, 30], [5, 7, 15, 30]],
      "ajax": {
        url:url,
        "dataSrc": "content"
      },
      "aoColumns": [
        { "mDataProp": "orderId" },
        { "mDataProp": "refundId" },
        { "mDataProp": "channelName" },
        { "mDataProp": "productName" },
        { "mDataProp": "buyerName" },
        { "mDataProp": "buyerDateStr" },
        { "mDataProp": "centerStateStr" },
        { "mDataProp": "centerDateStr" },
        { "mDataProp": "refundStateStr" },
        {"mDataProp":  "refundAmount"},
        { "mDataProp": null }
      ],

      "createdRow" : function(row, mDataProp, dataIndex){
        $(row).addClass('text-c');
      },

      "columnDefs" : [
        {
          "targets" : 10 ,
          "render" : function(mDataProp, type, full) {
            var str = '<td class="td-manage">';
            if(mDataProp.refundState == 1){
              str = str +'<a style="text-decoration:none" href="${pageContext.request.contextPath}/order/getRefundDetail/'+mDataProp.refundId+'.dhtml" title="查看"><i class="Hui-iconfont">&#xe624;</i></a>' +
                      '<a style="text-decoration:none" href="${pageContext.request.contextPath}/order/forward2ConfirmRefund/'+mDataProp.refundId+'.dhtml" title="确认退款"><i class="Hui-iconfont">&#xe615;</i></a>'
            }
            if(mDataProp.refundState == 3 || mDataProp.refundState == 2){
              str = str + '<a style="text-decoration:none" href="${pageContext.request.contextPath}/order/getRefundDetail/'+mDataProp.refundId+'.dhtml" title="查看"><i class="Hui-iconfont">&#xe624;</i></a>'
            }
            str = str + '</td>';
            return  str;
          }
        }
      ]
    });
  });
  /*根据条件查询*/
  function searchOrder(){
    var findBy = $("#findBy").val();
    var detail = $("#detail").val();
    var datemin = $("#datemin").val();
    var datemax = $("#datemax").val();
    url = '${pageContext.request.contextPath}'+'/order/getRefundMoneyByCondition.dhtml?findBy='+findBy +'&detail='+detail+'&datemin='+datemin+'&datemax='+datemax+"&refundType=1";
    targetTable.ajax.url(url).load();
  }

  $('.table-sort tbody').on('click', 'tr', function () {
    if ($(this).hasClass('selected')) {
      $(this).removeClass('selected');
    }
    else {
      $('table tr.selected').removeClass('selected');
      $(this).addClass('selected');
    }
  });


  /*图片-编辑*/
  function order_edit(title,url,id){
    var index = layer.open({
      type: 2,
      area: ['700px', '530px'],
      title: title,
      fix: false, //不固定
      maxmin: true,
      content: url
    });
    //layer.full(index);
  }
  /*图片-发货*/
  function order_deliver(title,url,id){
    var index = layer.open({
      type: 2,
      area: ['700px', '330px'],
      title: title,
      fix: false, //不固定
      maxmin: true,
      content: url
    });
    //layer.full(index);
  }
  /*图片-物流*/
  function order_logistics(title,url,id){
    var index = layer.open({
      type: 2,
      area: ['700px', '530px'],
      title: title,
      fix: false, //不固定
      maxmin: true,
      content: url
    });
    //layer.full(index);
  }
  /*图片-取消*/
  function order_cancel(title,url,id){
    var index = layer.open({
      type: 2,
      area: ['700px', '330px'],
      title: title,
      fix: false, //不固定
      maxmin: true,
      content: url
    });
    //layer.full(index);
  }
  /*图片-删除*/
  /* function product_del(obj,id){
   layer.confirm('确认要删除吗？',function(index){
   $(obj).parents("tr").remove();
   layer.msg('已删除!',{icon:1,time:1000});
   });
   } */

  /*审核-通过*/
  function order_verify(url){
    if(confirm("确认审核通过？")){
      $.post(
              url,
              function(data) {
                alert("审核通过");
                window.location.reload();
              }
      )
    }
  }


</script>
</body>