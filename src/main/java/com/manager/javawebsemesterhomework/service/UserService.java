package com.manager.javawebsemesterhomework.service;

import com.manager.javawebsemesterhomework.entity.VO.Response;

public interface UserService {
    public Response authenUser(String username, String password);
    public Response checkUser(String username);
    public Response modifyUser(String username , String password);

    public Response registerUser(String username, String password);

}
