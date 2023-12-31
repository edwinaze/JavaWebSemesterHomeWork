package com.manager.javawebsemesterhomework.entity.VO;

import lombok.Data;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/30 23:17
 */
@Data
public class Response<T>  {
    private boolean success;
    private String message;
    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> failure(String message) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
}
