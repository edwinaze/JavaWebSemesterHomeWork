package com.manager.javawebsemesterhomework.controller;

import com.manager.javawebsemesterhomework.entity.DO.Customer;
import com.manager.javawebsemesterhomework.entity.VO.PageCustomer;
import com.manager.javawebsemesterhomework.entity.VO.Response;
import com.manager.javawebsemesterhomework.service.CustomerService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ArrayUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/31 22:25
 */

@Controller
@Slf4j
public class StudentController {

    @Resource
    CustomerService customerService;

    @GetMapping("/student/index")
    public String indexNum(Model model, String current, String search_field, String keyword, HttpSession session) {
        String username = (String) session.getAttribute("username");

//        log.info("current: " + current);
        if(StringUtils.isEmpty(current)) {
            current = "1";
        }
        if(StringUtils.isEmpty(search_field)) {
            search_field = "";
        }
        if(StringUtils.isEmpty(keyword)) {
            keyword = "";
        }
        log.info("current: " + current + " search_field: " + search_field + " keyword: " + keyword);
        Integer currentNum = Integer.parseInt(current);
        PageCustomer page = null;

        if(search_field.isEmpty() || keyword.isEmpty()) {
            page = customerService.findAllByPage(currentNum - 1, 10);
        }
        else {
            page = customerService.findAllByPage(currentNum - 1, 10, search_field, keyword.trim());
        }

        List<Integer> pageList = new ArrayList<>();
        for(int i = 1; i <= page.getTotalPage(); i++) {
            pageList.add(i);
        }
        model.addAttribute("customerList", page.getData());
        model.addAttribute("currentPage", currentNum);
        model.addAttribute("totalPage", page.getTotalPage());
        model.addAttribute("total", page.getTotal());
        model.addAttribute("pageList", pageList);
        model.addAttribute("search_field", search_field);
        model.addAttribute("keyword", keyword);
        model.addAttribute("username", username);
        log.info("username" + username);

        return "student/index";
    }

    @GetMapping("/student/add")
    public String add(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "student/add";
    }
    @PostMapping(value = "/student/add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response add(Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PostMapping(value = "/student/delete", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response delete(Integer id) {
        return customerService.deleteCustomer(id);
    }

    @PostMapping(value = "/student/deleteAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response deleteAll(@RequestParam("ids[]") String[] ids) {
        log.info("ids: " + Arrays.toString(ids));
        return customerService.deleteAll(ids);
    }

    @PostMapping(value = "/student/find", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response find(Integer id) {
        return customerService.findCustomer(id);
    }

    @PostMapping(value = "/student/modify", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response modify(Customer customer) {
        return customerService.modifyCustomer(customer);
    }
}
