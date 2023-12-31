package com.manager.javawebsemesterhomework.service.impl;

import com.manager.javawebsemesterhomework.entity.DO.User;
import com.manager.javawebsemesterhomework.entity.VO.Response;
import com.manager.javawebsemesterhomework.repository.UserRepository;
import com.manager.javawebsemesterhomework.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/30 23:10
 */

@Component
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;
    @Override
    public Response authenUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return Response.failure("用户名不存在");
        }else if(!user.getPassword().equals(password)) {
            return Response.failure("密码错误");
        }
        return Response.success("登录成功");
    }

    @Override
    public Response checkUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return Response.success("用户名存在");
        }
        return Response.failure("用户名不存在");
    }
}
