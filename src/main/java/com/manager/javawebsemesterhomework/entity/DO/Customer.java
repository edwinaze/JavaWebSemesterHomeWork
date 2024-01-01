package com.manager.javawebsemesterhomework.entity.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/31 23:06
 */

@Data
@Entity
@Table(name = "customer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CNO")
    @Id
    Integer id;

    @Column(name = "CNAME")
    String name;

    @Column(name = "SEX")
    String sex;

    @Column(name="AGE")
    Integer age;
    @Column(name="ADDRESS")
    String address;
}
