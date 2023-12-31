package com.manager.javawebsemesterhomework.service;

import com.manager.javawebsemesterhomework.entity.VO.Response;

public interface UserService {
    public Response authenUser(String username, String password);
    public Response checkUser(String username);

}
