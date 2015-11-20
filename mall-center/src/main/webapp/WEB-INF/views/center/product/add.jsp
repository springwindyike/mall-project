<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String context = request.getContextPath();
    pageContext.setAttribute("context_", context);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/seller_center.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link href='${pageContext.request.contextPath}/resources/css/style.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/zTreeStyle.css" type="text/css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.ztree.core-3.5.js"></script>
<title>新增图片</title>
    <script type="text/javascript">
        var context_ = '${context_}/';
    </script>
</head>
<body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<div class="pd-20">
    <form:form modelAttribute="productAttribute" action="${pageContext.request.contextPath}/product/add.dhtml"  method="post" class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>产品标题：</label>
			<div class="formControls col-10">
			<form:input type="text" class="input-text" value="" placeholder="" id="" path="productName"/>
			 <form:input type="hidden" class="input-text" value="" placeholder="" id="url" path="url"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>分类栏目：</label>
			<div class="formControls col-2"> <span class="select-box">
				<!-- <input id ='selectType'class="select" value ='请点击选择商品分类'  readonly="readonly" onClick="order_edit()"/>  -->
			 	 	<form:input  id ='selectType'  readonly="readonly" class="select" value="请点击选择商品分类"  placeholder="" path="typeName" onClick="order_edit()"/> 
			 	 	<form:input  id ='typeCode'  readonly="readonly" class="select" value=""  placeholder="" path="typeCode" type='hidden'/>
			 	 		<form:input  id ='typeId'  readonly="readonly" class="select" value=""  placeholder="" path="typeId" type='hidden'/>
				</span> </div>
				<label class="form-label col-2"><span class="c-red">*</span>品牌栏目：</label>
			<div class="formControls col-2"> <span class="select-box">
				 <input id ='selectBrand'class="select" value ='请点击选择品牌分类'  readonly="readonly" onClick="brand_select()"/>
				<form:input  id ='brandId'  readonly="readonly" class="select" value=""  placeholder="" path="brandId" type='hidden'/>
				</span> </div>
				<label class="form-label col-2">产品库存：</label>
			<div class="formControls col-2">
				<form:input type="text" class="input-text" value="" placeholder="" id="" name=""  path="inventory"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">产品进价：</label>
			<div class="formControls col-4">
			<form:input type="text" path="basePrice" id="" placeholder="" value="" class="input-text" style="width:90%"/>
				元</div>
			<label class="form-label col-2">市场价格：</label>
			<div class="formControls col-4">
					<form:input type="text" path="marketPrice" id="" placeholder="" value="" class="input-text" style="width:90%"/>
				元</div>
		</div>
				<div class="ncsc-form-goods">
						<dl class="spec-bg" spec_img="t" nctype="spec_group_dl" nc_type="spec_group_dl_0">
						<dt>
						<input class="text w60 tip2 tr" type="text" data-param="{id:1,name:'颜色'}" nctype="spec_name" maxlength="4" value="颜色" title="自定义规格类型名称，规格值名称最多不超过4个字" name="sp_name[1]">
						：
						</dt>
						<dd nctype="sp_group_val">
						<ul class="spec">
						<li>
						<span nctype="input_checkbox">
						<input class="sp_val" type="checkbox" name="sp_val[1][456]" nc_type="456" value="红色">
						</span>
						<span nctype="pv_name">红色</span>
						</li>
						<li>
						<span nctype="input_checkbox">
						<input class="sp_val" type="checkbox" name="sp_val[1][459]" nc_type="459" value="黑色">
						</span>
						<span nctype="pv_name">黑色</span>
						</li>
						<li data-param="{gc_id:35,sp_id:1,url:'http://10.100.100.15/shop/index.php?act=store_goods_add&op=ajax_add_spec'}">
						<div nctype="specAdd1" style="display: block;">
						<a class="ncsc-btn" nctype="specAdd" href="javascript:void(0);">
						<i class="icon-plus"></i>
						添加规格值
						</a>
						</div>
						<div style="display: none;" nctype="specAdd2">
							<input class="text w60" type="text" maxlength="20" placeholder="规格值名称">
						<a class="ncsc-btn ncsc-btn-acidblue ml5 mr5" nctype="specAddSubmit" href="javascript:void(0);">确认</a>
						<a class="ncsc-btn ncsc-btn-orange" nctype="specAddCancel" href="javascript:void(0);">取消</a>
						</li>
						</ul>
						</dd>
						</dl>
						
						<dl class="spec-bg" nctype="spec_group_dl" nc_type="spec_group_dl_1">
						<dt>
						<input class="text w60 tip2 tr" type="text" data-param="{id:15,name:'尺码'}" nctype="spec_name" maxlength="4" value="尺码" title="自定义规格类型名称，规格值名称最多不超过4个字" name="sp_name[15]">
						：
						</dt>
						<dd>
						<ul class="spec">
						<li>
						<span nctype="input_checkbox">
						<input type="checkbox" name="sp_val[15][457]" nc_type="457" value="L">
						</span>
						<span nctype="pv_name">L</span>
						</li>
						<li>
						<span nctype="input_checkbox">
						<input type="checkbox" name="sp_val[15][458]" nc_type="458" value="X">
						</span>
						<span nctype="pv_name">X</span>
						</li>
						<li data-param="{gc_id:35,sp_id:15,url:'http://10.100.100.15/shop/index.php?act=store_goods_add&op=ajax_add_spec'}">
						<div nctype="specAdd1">
						<a class="ncsc-btn" nctype="specAdd" href="javascript:void(0);">
						<i class="icon-plus"></i>
						添加规格值
						</a>
						</div>
						<div style="display:none;" nctype="specAdd2">
						<input class="text w60" type="text" maxlength="20" placeholder="规格值名称">
						<a class="ncsc-btn ncsc-btn-acidblue ml5 mr5" nctype="specAddSubmit" href="javascript:void(0);">确认</a>
						<a class="ncsc-btn ncsc-btn-orange" nctype="specAddCancel" href="javascript:void(0);">取消</a>
						</div>
						</li>
						</ul>
						</dd>
						</dl>
						<dl style="overflow: visible; display: block;" class="spec-bg" nc_type="spec_dl">
        <dt>库存配置：</dt>
        <dd class="spec-dd">
          <table border="0" cellspacing="0" cellpadding="0" class="spec_table">
            <thead>
                                        <tr><th nctype="spec_name_1">颜色</th>
                          <th nctype="spec_name_15">尺码</th>
                                          <th class="w90"><span class="red">*</span>市场价
                <div class="batch"><i title="批量操作" class="icon-edit"></i>
                  <div style="display:none;" class="batch-input">
                    <h6>批量设置价格：</h6>
                    <a class="close" href="javascript:void(0)">X</a>
                    <input type="text" class="text price" name="">
                    <a data-type="marketprice" class="ncsc-btn-mini" href="javascript:void(0)">设置</a><span class="arrow"></span></div>
                </div></th>
              <th class="w90"><span class="red">*</span>价格                <div class="batch"><i title="批量操作" class="icon-edit"></i>
                  <div style="display:none;" class="batch-input">
                    <h6>批量设置价格：</h6>
                    <a class="close" href="javascript:void(0)">X</a>
                    <input type="text" class="text price" name="">
                    <a data-type="price" class="ncsc-btn-mini" href="javascript:void(0)">设置</a><span class="arrow"></span></div>
                </div></th>
              <th class="w60"><span class="red">*</span>库存                <div class="batch"><i title="批量操作" class="icon-edit"></i>
                  <div style="display:none;" class="batch-input">
                    <h6>批量设置库存：</h6>
                    <a class="close" href="javascript:void(0)">X</a>
                    <input type="text" class="text stock" name="">
                    <a data-type="stock" class="ncsc-btn-mini" href="javascript:void(0)">设置</a><span class="arrow"></span></div>
                </div></th>
              <th class="w70">预警值
                <div class="batch"><i title="批量操作" class="icon-edit"></i>
                  <div style="display:none;" class="batch-input">
                    <h6>批量设置预警值：</h6>
                    <a class="close" href="javascript:void(0)">X</a>
                    <input type="text" class="text stock" name="">
                    <a data-type="alarm" class="ncsc-btn-mini" href="javascript:void(0)">设置</a><span class="arrow"></span></div>
                </div></th>
              <th class="w100">商家货号</th>
                </tr></thead>
            <tbody nc_type="spec_table">
            </tbody>
          </table>
          <p class="hint">点击<i class="icon-edit"></i>可批量修改所在列的值。</p>
        </dd>
      </dl>
		</div>
		<div class="row cl">
			<label class="form-label col-2">图片上传：</label>
			<div class="formControls col-10">
				<div class="uploader-list-container">
					<div class="queueList">
						<div id="dndArea" class="placeholder">
							<div id="filePicker-2"></div>
							<p>或将照片拖到这里，单次最多可选300张</p>
						</div>
					</div>
					<div class="statusBar" style="display:none;">
						<div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
						<div class="info"></div>
						<div class="btns">
							<div id="filePicker2"></div>
							<div class="uploadBtn">开始上传</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">详细内容：</label>
			<div class="formControls col-10"> 
				<script id="editor" type="text/plain" style="width:100%;height:400px;"></script> 
				<script type="text/javascript">
				function getContent() {
					   // var arr = [];
					   $("#descri").value = UE.getEditor('editor').getContent();
					};
				</script> 
					<form:input type="hidden" id="descri" value="" path="description"/>
			</div>
		</div>
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
</form:form>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/ueditor/1.4.3/ueditor.config.js?1"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/H-ui.admin.js"></script> 
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.js"></script>  --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/seller.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/waypoints.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.validation.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/common.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/member.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/store_goods_add.step2.js"></script>
<script type="text/javascript">
$(function(){
	$("#showType").hide();
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$list = $("#fileList"),
	$btn = $("#btn-star"),
	state = "pending",
	uploader;

	var uploader = WebUploader.create({
		auto: true,
		swf: 'lib/webuploader/0.1.5/Uploader.swf',
	
		// 文件接收服务端。
		server: 'http://localhost:8100/center/ueditor/dispatch.dhtml?action=uploadimage',
	
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#filePicker',
	
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		// 只允许选择图片文件。
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		}
	});
	uploader.on( 'fileQueued', function( file ) {
		var $li = $(
			'<div id="' + file.id + '" class="item">' +
				'<div class="pic-box"><img></div>'+
				'<div class="info">' + file.name + '</div>' +
				'<p class="state">等待上传...</p>'+
			'</div>'
		),
		$img = $li.find('img');
		$list.append( $li );
	
		// 创建缩略图
		// 如果为非图片文件，可以不用调用此方法。
		// thumbnailWidth x thumbnailHeight 为 100 x 100
		uploader.makeThumb( file, function( error, src ) {
			if ( error ) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
	
			$img.attr( 'src', src );
		}, thumbnailWidth, thumbnailHeight );
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
		var $li = $( '#'+file.id ),
			$percent = $li.find('.progress-box .sr-only');
	
		// 避免重复创建
		if ( !$percent.length ) {
			$percent = $('<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>').appendTo( $li ).find('.sr-only');
		}
		$li.find(".state").text("上传中");
		$percent.css( 'width', percentage * 100 + '%' );
	});
	
	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file ) {
		$( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
	});
	
	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
		$( '#'+file.id ).addClass('upload-state-error').find(".state").text("上传出错");
	});
	
	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
		$( '#'+file.id ).find('.progress-box').fadeOut();
	});
	uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });

    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });

});

(function( $ ){
    // 当domReady的时候开始初始化
    $(function() {
        var $wrap = $('.uploader-list-container'),

            // 图片容器
            $queue = $( '<ul class="filelist"></ul>' )
                .appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
            $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $wrap.find( '.uploadBtn' ),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find( '.placeholder' ),

            $progress = $statusBar.find( '.progress' ).hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = ( function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if( this.width != 1 || this.height != 1 ) {
                        support = false;
                    }
                };
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            } )(),

            // 检测是否已经安装flash，检测flash的版本
            flashVersion = ( function() {
                var version;

                try {
                    version = navigator.plugins[ 'Shockwave Flash' ];
                    version = version.description;
                } catch ( ex ) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                .GetVariable('$version');
                    } catch ( ex2 ) {
                        version = '0.0';
                    }
                }
                version = version.match( /\d+/g );
                return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
            } )(),

            supportTransition = (function(){
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                s = null;
                return r;
            })(),

            // WebUploader实例
            uploader;

        if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function(container) {
                    window['expressinstallcallback'] = function( state ) {
                        switch(state) {
                            case 'Download.Cancelled':
                                alert('您取消了更新！');
                                break;

                            case 'Download.Failed':
                                alert('安装失败');
                                break;

                            default:
                                alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = 'expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' +  swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">'  +
                        '<param name="movie" value="' + swf + '" />' +
                        '<param name="wmode" value="transparent" />' +
                        '<param name="allowscriptaccess" value="always" />' +
                    '</object>';

                    container.html(html);

                })($wrap);

            // 压根就没有安转。
            } else {
                $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            alert( 'Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker-2',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: 'lib/webuploader/0.1.5/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: 'http://localhost:8100/center/ueditor/dispatch.dhtml?action=uploadimage',
            // runtimeOrder: 'flash',

            // accept: {
            //     title: 'Images',
            //     extensions: 'gif,jpg,jpeg,bmp,png',
            //     mimeTypes: 'image/*'
            // },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });
     	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
    	uploader.on( 'uploadSuccess', function( file,response ) {
    		$("#url").val($("#url")[0].value+"_"+response.url);
    		$( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
    	});
        // 拖拽时不接受 js, txt 文件。
        uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });

        uploader.on('dialogOpen', function() {
            console.log('here');
        });

        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        uploader.on('ready', function() {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
                $info = $('<p class="error"></p>'),

                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        img = $('<img src="'+src+'">');
                        $wrap.empty().append( img );
                    } else {
                        $.ajax('http://localhost:8100/center/ueditor/dispatch.dhtml', {
                            method: 'POST',
                            data: src,
                            dataType:'json'
                        }).done(function( response ) {
                            if (response.result) {
                                img = $('<img src="'+response.result+'">');
                                $wrap.empty().append( img );
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                }


            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;


            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $( '#filePicker2' ).removeClass( 'element-invisible' );
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        alert( '上传成功' );
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });

        uploader.onError = function( code ) {
            alert( 'Eroor: ' + code );
        };

        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();
    });

})( jQuery );

$(function(){

	var ue = UE.getEditor('editor');
});

function order_edit(){
	 var str="";
	 $.ajax({
        type: "get",
        dataType: "json",
        url: "${pageContext.request.contextPath}/productType/firstLevel.dhtml",
        success: function (msg) {
       			 	var jsonData = eval(msg);
		        	$.each(jsonData.child, function(index, jsonOne) {
		         str+="<div><tr id =productName><a onclick=dispaly_child_sort("+jsonOne.id+")>"+jsonOne.typeName+"</a></tr></div>";}	)	;
		        	layer_open(str,'选择分类');
                         }}
		)
};

 function brand_select(){
	 var str="";
	  $.ajax({
       type: "get",
       dataType: "json",
       url: "${pageContext.request.contextPath}/brand/allBrandList.dhtml",
       success: function (msg) {
      			 	var jsonData = eval(msg);
		        	$.each(jsonData, function(index, jsonOne) {
		         str+='<div><tr id =brandId><a onclick="brand_click(\''+jsonOne.id+'\',\''+jsonOne.name+'\')">'+jsonOne.name+'</a></tr></div>';}	)	;
		        	/* '<a href="javascript:showWT(\''+wt_id+'\',\''+wt_title+'\')">'+wt_title+'</a>'; */
		        	layer_open(str,'选择分类');
                        }}
		) 
	 layer_open(str,'选择品牌');
}; 

function layer_open(i,title) {
	layer.closeAll('page');
	var index =layer.open({
	    type: 1,
	    title: title,
	    closeBtn: true,
	    shadeClose: true,
	    area: ['700px', '530px'],
	    content: i
	});
};
function brand_click(id,name){
	layer.closeAll('page');
 	$("#selectBrand").val(name);
 	$("#brandId").val(id);
}; 
var i = 0;
function dispaly_child_sort(parentId){
	 var str="";
	 if (i==2){
			$.ajax({
		        type: "get",
		        dataType: "json",
		        url: "${pageContext.request.contextPath}/productType/findById/"+parentId+".dhtml",
		        success: function (msg) {
		        	layer.closeAll('page');
		        	i=0;
		        	$("#selectType").val(msg.typeName);
		        	$("#typeCode").val(msg.code);
		         	$("#typeId").val(msg.id);
		                       }
		              })
	 } else {
			$.ajax({
		        type: "get",
		        dataType: "json",
		        url: "${pageContext.request.contextPath}/productType/childLevel/"+parentId+".dhtml",
		        success: function (msg) {
		       			 	var jsonData = eval(msg);
				        	$.each(jsonData.child, function(index, jsonOne) {
				        		 str+="<div><tr id =productName><a onclick=dispaly_child_sort("+jsonOne.id+")>"+jsonOne.typeName+"</a></tr></div>";}	)	;
				        	layer_open(str);
				        	i++;
		                       }
		              })
	 }
};
var spec_group_checked = ['',''];
var str = '';
var V = new Array();

var spec_group_checked_0 = new Array();
var spec_group_checked_1 = new Array();

$(function(){
	$('dl[nctype="spec_group_dl"]').on('click', 'span[nctype="input_checkbox"] > input[type="checkbox"]',function(){
		into_array();
		goods_stock_set();
	});

	// 提交后不没有填写的价格或库存的库存配置设为默认价格和0
	// 库存配置隐藏式 里面的input加上disable属性
	$('input[type="submit"]').click(function(){
		$('input[data_type="price"]').each(function(){
			if($(this).val() == ''){
				$(this).val($('input[name="g_price"]').val());
			}
		});
		$('input[data_type="stock"]').each(function(){
			if($(this).val() == ''){
				$(this).val('0');
			}
		});
		$('input[data_type="alarm"]').each(function(){
			if($(this).val() == ''){
				$(this).val('0');
			}
		});
		if($('dl[nc_type="spec_dl"]').css('display') == 'none'){
			$('dl[nc_type="spec_dl"]').find('input').attr('disabled','disabled');
		}
	});
	
});

// 将选中的规格放入数组
function into_array(){
		
		spec_group_checked_0 = new Array();
		$('dl[nc_type="spec_group_dl_0"]').find('input[type="checkbox"]:checked').each(function(){
			i = $(this).attr('nc_type');
			v = $(this).val();
			c = null;
			if ($(this).parents('dl:first').attr('spec_img') == 't') {
				c = 1;
			}
			spec_group_checked_0[spec_group_checked_0.length] = [v,i,c];
		});

		spec_group_checked[0] = spec_group_checked_0;

		
		spec_group_checked_1 = new Array();
		$('dl[nc_type="spec_group_dl_1"]').find('input[type="checkbox"]:checked').each(function(){
			i = $(this).attr('nc_type');
			v = $(this).val();
			c = null;
			if ($(this).parents('dl:first').attr('spec_img') == 't') {
				c = 1;
			}
			spec_group_checked_1[spec_group_checked_1.length] = [v,i,c];
		});

		spec_group_checked[1] = spec_group_checked_1;

}

// 生成库存配置
function goods_stock_set(){
    //  店铺价格 商品库存改为只读
    $('input[name="g_price"]').attr('readonly','readonly').css('background','#E7E7E7 none');
    $('input[name="g_storage"]').attr('readonly','readonly').css('background','#E7E7E7 none');

    $('dl[nc_type="spec_dl"]').show();
    str = '<tr>';
    for (var i_0=0; i_0<spec_group_checked[0].length; i_0++){td_1 = spec_group_checked[0][i_0];
for (var i_1=0; i_1<spec_group_checked[1].length; i_1++){td_2 = spec_group_checked[1][i_1];
var tmp_spec_td = new Array();
tmp_spec_td[0] = td_1[1];
tmp_spec_td[1] = td_2[1];
tmp_spec_td.sort(function(a,b){return a-b});
var spec_bunch = 'i_';
spec_bunch += tmp_spec_td[0];
spec_bunch += tmp_spec_td[1];
str += '<input type="hidden" name="spec['+spec_bunch+'][goods_id]" nc_type="'+spec_bunch+'|id" value="" />';if (td_1[2] != null) { str += '<input type="hidden" name="spec['+spec_bunch+'][color]" value="'+td_1[1]+'" />';}str +='<td><input type="hidden" name="spec['+spec_bunch+'][sp_value]['+td_1[1]+']" value="'+td_1[0]+'" />'+td_1[0]+'</td>';
if (td_2[2] != null) { str += '<input type="hidden" name="spec['+spec_bunch+'][color]" value="'+td_2[1]+'" />';}str +='<td><input type="hidden" name="spec['+spec_bunch+'][sp_value]['+td_2[1]+']" value="'+td_2[0]+'" />'+td_2[0]+'</td>';
str +='<td><input class="text price" type="text" name="spec['+spec_bunch+'][marketprice]" data_type="marketprice" nc_type="'+spec_bunch+'|marketprice" value="" /><em class="add-on"><i class="icon-renminbi"></i></em></td><td><input class="text price" type="text" name="spec['+spec_bunch+'][price]" data_type="price" nc_type="'+spec_bunch+'|price" value="" /><em class="add-on"><i class="icon-renminbi"></i></em></td><td><input class="text stock" type="text" name="spec['+spec_bunch+'][stock]" data_type="stock" nc_type="'+spec_bunch+'|stock" value="" /></td><td><input class="text stock" type="text" name="spec['+spec_bunch+'][alarm]" data_type="alarm" nc_type="'+spec_bunch+'|alarm" value="" /></td><td><input class="text sku" type="text" name="spec['+spec_bunch+'][sku]" nc_type="'+spec_bunch+'|sku" value="" /></td></tr>';
}
}
    if(str == '<tr>'){
        //  店铺价格 商品库存取消只读
        $('input[name="g_price"]').removeAttr('readonly').css('background','');
        $('input[name="g_storage"]').removeAttr('readonly').css('background','');
        $('dl[nc_type="spec_dl"]').hide();
    }else{
        $('tbody[nc_type="spec_table"]').empty().html(str)
            .find('input[nc_type]').each(function(){
                s = $(this).attr('nc_type');
                try{$(this).val(V[s]);}catch(ex){$(this).val('');};
                if ($(this).attr('data_type') == 'marketprice' && $(this).val() == '') {
                    $(this).val($('input[name="g_marketprice"]').val());
                }
                if ($(this).attr('data_type') == 'price' && $(this).val() == ''){
                    $(this).val($('input[name="g_price"]').val());
                }
                if ($(this).attr('data_type') == 'stock' && $(this).val() == ''){
                    $(this).val('0');
                }
                if ($(this).attr('data_type') == 'alarm' && $(this).val() == ''){
                    $(this).val('0');
                }
            }).end()
            .find('input[data_type="stock"]').change(function(){
                computeStock();    // 库存计算
            }).end()
            .find('input[data_type="price"]').change(function(){
                computePrice();     // 价格计算
            }).end()
            .find('input[nc_type]').change(function(){
                s = $(this).attr('nc_type');
                V[s] = $(this).val();
            });
    }
}

</script>
</body>
</html>