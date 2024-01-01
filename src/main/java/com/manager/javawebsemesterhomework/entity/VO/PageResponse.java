package com.manager.javawebsemesterhomework.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2024/1/1 1:18
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private boolean success;
    private String message;
    private T data;
    Integer total;
    Integer current;
    Integer size;

    public static <T> PageResponse<T> success(T data, Integer total, Integer current, Integer size) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setSuccess(true);
        pageResponse.setData(data);
        pageResponse.setTotal(total);
        pageResponse.setCurrent(current);
        pageResponse.setSize(size);
        return pageResponse;
    }

    public static <T> PageResponse<T> failure(String message) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setSuccess(false);
        pageResponse.setMessage(message);
        return pageResponse;
    }
}
