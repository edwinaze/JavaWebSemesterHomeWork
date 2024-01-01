package com.manager.javawebsemesterhomework.entity.DO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GeneratedColumn;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/30 23:04
 */

@Data
@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //生成策略，这里配置为自增
    @Column(name = "id")    //对应表中id这一列
    @Id     //此属性为主键
    Integer id;
    @Column(name = "username")
    String username;
    @Column(name="password")
    String password;
}
