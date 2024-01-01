package com.manager.javawebsemesterhomework.service;

import com.manager.javawebsemesterhomework.entity.DO.Customer;
import com.manager.javawebsemesterhomework.entity.VO.PageCustomer;
import com.manager.javawebsemesterhomework.entity.VO.Response;

import java.util.List;

public interface CustomerService {
    public PageCustomer findAllByPage(int current, int size);
    public PageCustomer findAllByPage(int current, int size, String search_field, String keyword);

    public Response addCustomer(Customer customer);
    public Integer findAllCount();

    public Response deleteCustomer(Integer id);

    public Response findCustomer(Integer id);

    public Response modifyCustomer(Customer customer);
}
