package com.ishare.mall.center.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.annoation.PageRequest;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.center.controller.test.Person;
import com.ishare.mall.center.controller.test.PersonJsonObject;
import com.ishare.mall.center.form.register.RegisterForm;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.MemberPermissionDTO;
import com.ishare.mall.common.base.dto.member.MemberRegisterDTO;
import com.ishare.mall.common.base.dto.member.MemberRegisterResultDTO;
import com.ishare.mall.common.base.dto.page.PageRequestDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;



/**
 * Created by ZhangZhaoxin on 2015/9/14.
 * Description :
 * Version 1.0
 */
@Controller
public class OrderController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public static Logger getLog() {
        return log;
    }

    @RequestMapping("/res")
    @ResponseBody
    public Object result(Model m) {
    	System.out.println("________________");
        return m;
    }

//    @RequestMapping(value = CenterURIConstant.Index.INDEX)
//    public String index(){
//        return CenterViewConstant.Index.LOGIN;
//    }

    @RequestMapping(value = CenterURIConstant.Index.LOGIN, method = RequestMethod.GET)
    public String login() {
        log.debug(this.bizAppUrl);
        return CenterViewConstant.Index.LOGIN;
    }
    
    @RequestMapping(value = CenterURIConstant.Index.LOGIN, method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        log.debug("error" + error);
        model.addAttribute("error", error);
//    	if(null != loginForm.getVerifyCode()){
//
//    		if (!(loginForm.getVerifyCode().equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
//    			System.out.println("验证码不正确");
//    			return CenterViewConstant.Index.LOGIN;
//    		}else {
//    			System.out.println("验证码正确");
//    			MemberLoginDTO memberLoginDTO = new MemberLoginDTO();
//    			memberLoginDTO.setAccount(loginForm.getAccount());
//    			memberLoginDTO.setPassword(loginForm.getPassword());
//    			log.debug(memberLoginDTO.toString());
//    			ResponseEntity<MemberLoginResultDTO> resultDTO = null;
//    			RestTemplate restTemplate = new RestTemplate();
//    			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_LOGIN), memberLoginDTO, MemberLoginResultDTO.class);
//    			MemberLoginResultDTO memberLoginResultDTO = resultDTO.getBody();
//    			log.debug(memberLoginResultDTO.toString());
//    			return "redirect:/index.dhtml";
//    		}
//    	}
   return CenterViewConstant.Index.LOGIN;
    }
    
    /**
     * 访问注册页面
     * @return
     */
    @RequestMapping(value = CenterURIConstant.Index.REGISTER, method = RequestMethod.GET)
    public String register() {
        return CenterViewConstant.Index.REGISTER;
    }
    /**
     * 注册提交
     * @return
     */
    @ResponseBody
    @RequestMapping(value = CenterURIConstant.Index.REGISTER, method = RequestMethod.POST)
    public MemberRegisterResultDTO registerMember(@ModelAttribute("registerAttribute") RegisterForm registerForm) {
    			log.debug(registerForm.toString());
    			MemberRegisterDTO memberRegisterDTO = new MemberRegisterDTO();
    			BeanUtils.copyProperties(registerForm,memberRegisterDTO);
    			ResponseEntity<MemberRegisterResultDTO> resultDTO = null;
    			RestTemplate restTemplate = new RestTemplate();
    			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING,APPURIConstant.Member.REQUEST_MAPPING_REGISTER_MEMBER),memberRegisterDTO,MemberRegisterResultDTO.class);
    			MemberRegisterResultDTO memberRegisterResultDTO = resultDTO.getBody();   			
       return memberRegisterResultDTO;
    }
    
    /**
     * 注册账号验证
     * @return
     */
    @ResponseBody
    @RequestMapping(value = CenterURIConstant.Index.ACCOUNTVALID, method = RequestMethod.POST)
    public ValidformRespDTO accountValid(@RequestParam("name") String name, @RequestParam("param") String param) {
    	MemberRegisterDTO memberRegisterDTO = new MemberRegisterDTO();
    	memberRegisterDTO.setAccount(param);
			ResponseEntity<ValidformRespDTO> resultDTO = null;
			RestTemplate restTemplate = new RestTemplate();
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_VALID_BY_ACCOUNT), memberRegisterDTO, ValidformRespDTO.class);
			ValidformRespDTO validformRespDTO = resultDTO.getBody();
			return validformRespDTO;
    }
    /**
     * 访问找回密码页面
     * @return
     */
    @RequestMapping(value = CenterURIConstant.Index.FIND_PASSWORD, method = RequestMethod.GET)
    public String findPassword() {
        return CenterViewConstant.Index.FIND_PASSWORD;
    }
    
    @RequestMapping(value ="test")
    public String test() {
        log.debug("here");
        ResponseEntity<MemberPermissionDTO> resultDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        log.debug(this.buildBizAppURI(APPURIConstant.Permission.REQUEST_MAPPING,"") + "/13885268940");
        resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.Permission.REQUEST_MAPPING,"") + "/13885268940", MemberPermissionDTO.class);
        MemberPermissionDTO memberPermissionDTO = resultDTO.getBody();
        log.debug(memberPermissionDTO.toString());
        return CenterViewConstant.Index.LOGIN;
    }

    @RequestMapping(value = "table")
    public String testDataTables() {
        return "table";
    }
    @RequestMapping(value = "table/data", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public PersonJsonObject testDataTables(@PageRequest PageRequestDTO pageRequestDTO, HttpServletRequest request) {
        log.debug("hhahhahahah....");
        log.debug("pageRequestDTO.pageSize : " + pageRequestDTO.getPageSize());
        //Fetch the page number from client
        Integer pageNumber = pageRequestDTO.getCurrentPage();
        if (null != request.getParameter("iDisplayStart")) {
            log.debug("iDisplayStart : " + request.getParameter("iDisplayStart"));
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageRequestDTO.getPageSize()) + 1;
        }

        log.debug("pageNumber : " + pageNumber);

        //Fetch search parameter
        String searchParameter = request.getParameter("sSearch");

        //Fetch Page display length
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

        //Create page list data
        List<Person> personsList = createPaginationData(pageDisplayLength);

        //Here is server side pagination logic. Based on the page number you could make call
        //to the data base create new list and send back to the client. For demo I am shuffling
        //the same list to show data randomly
        if (pageNumber == 1) {
            Collections.shuffle(personsList);
        }else if (pageNumber == 2) {
            Collections.shuffle(personsList);
        }else {
            Collections.shuffle(personsList);
        }

        //Search functionality: Returns filtered list based on search parameter
        personsList = getListBasedOnSearchParameter(searchParameter,personsList);


        PersonJsonObject personJsonObject = new PersonJsonObject();
        //Set Total display record
        personJsonObject.setiTotalDisplayRecords(500);
        //Set Total record
        personJsonObject.setiTotalRecords(500);
        personJsonObject.setAaData(personsList);

        return personJsonObject;
    }

    private List<Person> getListBasedOnSearchParameter(String searchParameter,List<Person> personsList) {

        if (null != searchParameter && !searchParameter.equals("")) {
            List<Person> personsListForSearch = new ArrayList<Person>();
            searchParameter = searchParameter.toUpperCase();
            for (Person person : personsList) {
                if (person.getName().toUpperCase().indexOf(searchParameter)!= -1 || person.getOffice().toUpperCase().indexOf(searchParameter)!= -1
                        || person.getPhone().toUpperCase().indexOf(searchParameter)!= -1 || person.getPosition().toUpperCase().indexOf(searchParameter)!= -1
                        || person.getSalary().toUpperCase().indexOf(searchParameter)!= -1 || person.getStart_date().toUpperCase().indexOf(searchParameter)!= -1) {
                    personsListForSearch.add(person);
                }

            }
            personsList = personsListForSearch;
            personsListForSearch = null;
        }
        return personsList;
    }

    private List<Person> createPaginationData(Integer pageDisplayLength) {
        List<Person> personsList = new ArrayList<Person>();
        for (int i = 0; i < 1; i++) {
            Person person2 = new Person();
            person2.setName("John Landy");
            person2.setPosition("System Architect");
            person2.setSalary("$320,800");
            person2.setOffice("NY");
            person2.setPhone("999999999");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Igor Vornovitsky");
            person2.setPosition("Solution Architect");
            person2.setSalary("$340,800");
            person2.setOffice("NY");
            person2.setPhone("987897899");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Java Honk");
            person2.setPosition("Architect");
            person2.setSalary("$380,800");
            person2.setOffice("NY");
            person2.setPhone("1234567890");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Ramesh Arrepu");
            person2.setPosition("Sr. Architect");
            person2.setSalary("$310,800");
            person2.setOffice("NY");
            person2.setPhone("4654321234");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

            person2 = new Person();
            person2.setName("Bob Sidebottom");
            person2.setPosition("Architect");
            person2.setSalary("$300,800");
            person2.setOffice("NJ");
            person2.setPhone("9876543212");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);

        }

        for (int i = 0; i < pageDisplayLength-5; i++) {
            Person person2 = new Person();
            person2.setName("Zuke Torres");
            person2.setPosition("System Architect");
            person2.setSalary("$320,800");
            person2.setOffice("NY");
            person2.setPhone("999999999");
            person2.setStart_date("05/05/2010");
            personsList.add(person2);
        }
        return personsList;
    }
}
