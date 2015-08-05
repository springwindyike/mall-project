package com.ishare.mall.manage.controllers;

import com.ishare.mall.manage.form.CustomerForm;
import com.ishare.mall.manage.utils.Servlets;
import com.ishare.mall.old.model.Customer;
import com.ishare.mall.old.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

//import org.apache.commons.beanutils.BeanUtils;

/**
 * Created by dongqi on 15/7/23.
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 默认页面
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "customer/list";
    }

    /**
     * 获取用户json
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getData(final HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("page"));
        int pageSize = Integer.valueOf(request.getParameter("rows"));

        PageRequest pageRequest = new PageRequest(currentPage - 1, pageSize, Sort.Direction.DESC, "id");

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        log.debug("searchParams: {}", searchParams);
        Page<Customer> result = customerService.search(searchParams, pageRequest);

        log.debug("result {}", result.getContent());
        return getEasyUIData(result);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        return updateForm(id, model, "update");
    }

    @RequestMapping(value = "lock/{id}", method = RequestMethod.GET)
    public String lock(@PathVariable("id") Long id, Model model) {
        return updateForm(id, model, "lock");
    }

    @RequestMapping(value = "unlock/{id}", method = RequestMethod.GET)
    public String unlock(@PathVariable("id") Long id, Model model) {
        return updateForm(id, model, "unlock");
    }

    private String updateForm(Long id, Model model, String action) {
        Customer customer = customerService.get(id);
        log.debug("\n\tid={}\n\taction={}\n\tcustomer={}", id, action, customer);
        model.addAttribute("customer", customer);
        model.addAttribute("lockZH", customer.getLock() ? "已锁定" : "未锁定");
        model.addAttribute("action", action);
        return "customer/form";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@Valid @ModelAttribute @RequestBody CustomerForm form, Model model) {
        Customer customer = customerService.get(form.getId());
        BeanUtils.copyProperties(form, customer, "id", "password", "lock");

        log.debug("\n\tupdate customer: \n\t\tform={}\n\t\tentity={}", form.toString(), customer.toString());
        customerService.update(customer);
        return "success";
    }

    @RequestMapping(value = "lock", method = RequestMethod.POST)
    @ResponseBody
    public String lock(@RequestParam(value = "id") Long id) {
        return modifyLockStatus(id, Boolean.TRUE);
    }

    @RequestMapping(value = "unlock", method = RequestMethod.POST)
    @ResponseBody
    public String unlock(@RequestParam(value = "id") Long id) {
        return modifyLockStatus(id, Boolean.FALSE);
    }

    private String modifyLockStatus(Long id, Boolean isLock) {
        Customer customer = customerService.get(id);
        customer.setLock(isLock);
        customerService.update(customer);
        return "success";
    }
}
