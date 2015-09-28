package com.ishare.mall.center.controller.test;

import com.ishare.mall.common.base.dto.oauth.OAuthDTO;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YinLin on 2015/9/24.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping("/token")
public class TestTokenController {

    private static final Logger log = LoggerFactory.getLogger(TestTokenController.class);

    private String client_id;

    private String client_secret;

    private String grant_type;

    private String code;

    private String redirect_uri;

    @RequestMapping("/get")
    public String getCode(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("user", "hhahah");
        Map<String, String> params = new HashMap<>();
        params.put("client_id", "123");
        params.put("response_type", "code");
        params.put("account", "13800138000");
        params.put("redirect_uri", "http://127.0.0.1:8100/center/token/code.dhtml");
        String submitHtml = this.buildRequest(params, "GET", "获取", "http://127.0.0.1:8000/api/authorize");
        model.addAttribute("submitHtml", submitHtml);
        return "codeSubmit";
    }
    @RequestMapping("/token")
    public ResponseEntity accessToken() {
        ResponseEntity<Response> responseEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap();
        params.put("client_id", "123");
        params.put("response_type", "code");
        params.put("account", "13800138000");
        responseEntity = restTemplate.getForEntity("http://127.0.0.1:8000/api/authorize?client_id=123&response_type=code&account=13800138000", Response.class);
        Response response = responseEntity.getBody();
        log.debug((String)response.getData());
        TestTokenForm testTokenForm = new TestTokenForm();
        testTokenForm.setCode((String) response.getData());
        testTokenForm.setClient_id("123");
        testTokenForm.setClient_secret("123");
        testTokenForm.setGrant_type("authorization_code");
        ResponseEntity<Response<OAuthDTO>> responseResponseEntity = null;
        //restTemplate.exchange("http://127.0.0.1:8000/api/accessToken?client_id=123&client_secret=123&code=" + (String) response.getData() + "&grant_type=authorization_code",
//        responseResponseEntity = restTemplate.exchange("http://127.0.0.1:8000/api/accessToken",
//                HttpMethod.POST, new HttpEntity<TestTokenForm>(testTokenForm),
//                new ParameterizedTypeReference<Response<OAuthDTO>>() {} );
        responseResponseEntity = restTemplate.exchange("http://127.0.0.1:8000/api/accessToken?redirect_uri=xxx.ccc&client_id=123&client_secret=123&code=" + (String) response.getData() + "&grant_type=authorization_code",
                HttpMethod.POST, null,
                new ParameterizedTypeReference<Response<OAuthDTO>>() {} );
        return new ResponseEntity(responseResponseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping("/code")
    public String getAccessToken(HttpServletRequest request, Model model) {
        log.debug("session : " + request.getSession().getAttribute("user"));
        TestTokenForm form = new TestTokenForm();
        log.debug("Here!!!");
        if (form.getCode() != null) {
            log.debug(form.getCode());
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", "123");
        params.put("client_secret", "123");
        params.put("grant_type", "authorization_code");
        params.put("code", request.getParameter("code"));
        params.put("redirect_uri", "http://127.0.0.1:8100/center/token/back.dhtml");
        String submitHtml = this.buildRequest(params, "POST", "获取", "http://127.0.0.1:8000/api/accessToken");
        model.addAttribute("submitHtml", submitHtml);
        return "codeSubmit";
    }
    @RequestMapping("/back")
    @ResponseBody
    public TestTokenForm getAccessTokenBack(TestTokenForm form, Model model, HttpServletRequest request) {
        log.debug("Token:" + request.getAttribute("access_token"));
        log.debug("expires_in:" + request.getAttribute("expires_in"));
        return form;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     *
     * @param sParaTemp     请求参数数组
     * @param strMethod     提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    private String buildRequest(Map<String, String> sParaTemp,
                                String strMethod, String strButtonName, String url) {
        // 待请求参数数组
        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<form target=\"_self\" name=\"access\" action=\""
                + url
                + "\" method=\"" + strMethod + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sParaTemp.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name
                    + "\" value=\"" + value + "\"/>");
        }
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName
                + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['access'].submit();</script>");
        log.debug(sbHtml.toString());
        return sbHtml.toString();
    }
}
