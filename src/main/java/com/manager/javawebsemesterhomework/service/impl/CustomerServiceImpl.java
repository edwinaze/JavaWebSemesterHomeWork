package com.manager.javawebsemesterhomework.service.impl;

import com.manager.javawebsemesterhomework.entity.DO.Customer;
import com.manager.javawebsemesterhomework.entity.VO.PageCustomer;
import com.manager.javawebsemesterhomework.entity.VO.Response;
import com.manager.javawebsemesterhomework.repository.CustomerRepository;
import com.manager.javawebsemesterhomework.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/31 23:50
 */

@Component
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerRepository customerRepository;

    @Override
    public PageCustomer findAllByPage(int current, int size) {
        PageRequest request = PageRequest.of(current, size);
        Page<Customer> page = customerRepository.findAll(request);
        return PageCustomer.builder()
                .data(page.getContent())
                .total(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public PageCustomer findAllByPage(int current, int size, String search_field, String keyword) {
        PageRequest request = PageRequest.of(current, size);
        Page<Customer> page = null;
        if(search_field.equals("id")) {
            page = customerRepository.findAllByIdContaining(Integer.parseInt(keyword), request);
        }
        else if(search_field.equals("name")) {
            page = customerRepository.findAllByNameLike("%" + keyword + "%", request);
        }
        else if(search_field.equals("address")) {
            page = customerRepository.findAllByAddressLike("%" + keyword + "%", request);
        }
        else {
            page = customerRepository.findAll(request);
        }
        return PageCustomer.builder()
                .data(page.getContent())
                .total(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public Response addCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
            return Response.success("添加成功");
        }
        catch (Exception e) {
            return Response.failure("添加失败");
        }
    }

    @Override
    public Integer findAllCount() {
        return customerRepository.findAll().size();
    }

    @Override
    public Response deleteCustomer(Integer id) {
        try {
            customerRepository.deleteById(id);
            return Response.success("删除成功");
        }
        catch (Exception e) {
            return Response.failure("删除失败");
        }
    }

    @Override
    public Response findCustomer(Integer id) {
        try {
            Customer customer = customerRepository.findById(id).get();
            return Response.success(customer);
        }
        catch (Exception e) {
            return Response.failure("查找失败");
        }
    }

    @Override
    public Response modifyCustomer(Customer customer) {
        Customer old = customerRepository.findById(customer.getId()).get();
        if(old.equals(customer)) {
            return Response.failure("修改失败，和原数据一致");
        }
        else {
            try {
                customerRepository.save(customer);
                return Response.success("修改成功");
            }
            catch (Exception e) {
                return Response.failure("修改失败，未知错误");
            }
        }

    }
}
