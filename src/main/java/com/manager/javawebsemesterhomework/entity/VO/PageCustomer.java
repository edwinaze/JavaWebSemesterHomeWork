package com.manager.javawebsemesterhomework.entity.VO;

import com.manager.javawebsemesterhomework.entity.DO.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2024/1/1 16:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageCustomer {
    List<Customer> data;
    Long total;
    Integer totalPage;
}
