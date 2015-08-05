<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>修改会员信息</title>
    <%@ include file="/WEB-INF/views/include/easyui.jsp" %>

</head>
<body>
<div>
    <form id="mainform" action="${ctx }/customer/${action}" method="post">
        <table class="formTable">
            <tr>
                <td>账号：</td>
                <td>
                    <input type="hidden" name="id" value="${id }"/>
                    <input id="account" name="account" class="easyui-validatebox" data-options="width: 150"
                           value="${customer.account }">
                </td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td>
                    <input id="realName" name="realName" class="easyui-validatebox" data-options="width: 150"
                           value="${customer.realName }">
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <input type="radio" id="man" name="gender" value="0"/><label for="man">男</label>
                    <input type="radio" id="woman" name="gender" value="1"/><label for="woman">女</label>
                </td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td><input type="text" name="email" value="${customer.email}" class="easyui-validatebox"
                           data-options="width: 150,validType:'email'"/></td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input type="text" name="mobile" value="${customer.mobile}" class="easyui-numberbox"
                           data-options="width: 150"/></td>
            </tr>
            <tr>
                <td>状态：</td>
                <td><input type="text" name="lockZH" value="${lockZH}" class="easyui-validatebox"
                           data-options="width: 150"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    var action = "${action}";
    //用户 添加修改区分
    if (action == 'update') {
        $("input[name='gender'][value=${customer.gender}]").attr("checked", true);
        $("input[name='lock'][value=${customer.gender}]").attr('readonly', 'readonly');
    } else {
        $("input[name='gender'][value=${customer.gender}]").attr("checked", true);
        $(':input').attr('readonly', 'readonly');

    }

    //提交表单
    $('#mainform').form({
        onSubmit: function () {
            var isValid = $(this).form('validate');
            return isValid;	// 返回false终止表单提交
        },
        success: function (data) {
            successTip(data, dg, d);
        }
    });
</script>
</body>
</html>