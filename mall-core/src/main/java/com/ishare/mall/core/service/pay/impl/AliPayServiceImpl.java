package com.ishare.mall.core.service.pay.impl;

import com.ishare.mall.core.service.pay.AliPayService;
import org.springframework.stereotype.Service;

/**
 * Created by YinLin on 2015/8/14.
 * Description :
 * Version 1.0
 */
@Service
public class AliPayServiceImpl implements AliPayService {
    @Override
    public String create() {
        return null;
    }

    private String buildPayForm() {
        return null;
    }


    /**
     * 建立请求，以表单HTML形式构造（默认）
     *
     * @param sParaTemp     请求参数数组
     * @param strMethod     提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
//    private String buildRequest(Map<String, String> sParaTemp,
//                                String strMethod, String strButtonName) {
//        // 待请求参数数组
//        Map<String, String> sPara = buildRequestPara(sParaTemp);
//        List<String> keys = new ArrayList<String>(sPara.keySet());
//
//        StringBuffer sbHtml = new StringBuffer();
//
//        sbHtml
//                .append("<form target=\"_self\" id=\"alipaysubmit\" name=\"alipaysubmit\" action=\""
//                        + gatewayUrl
//                        + "_input_charset="
//                        + INPUT_CHARSET
//                        + "\" method=\"" + strMethod + "\">");
//
//        for (int i = 0; i < keys.size(); i++) {
//            String name = (String) keys.get(i);
//            String value = (String) sPara.get(name);
//
//            sbHtml.append("<input type=\"hidden\" name=\"" + name
//                    + "\" value=\"" + value + "\"/>");
//        }
//
//        // submit按钮控件请不要含有name属性
//        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName
//                + "\" style=\"display:none;\"></form>");
//        sbHtml
//                .append("<script>document.forms['alipaysubmit'].submit();</script>");
//        logger.debug(sbHtml.toString());
//        return sbHtml.toString();
//    }
}
