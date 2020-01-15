package com.robin.usedbookmarketbackend.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    private Integer userid;

    private String username;

    private String password;

    private Integer userstate;

    private String salt;

    private Date createTime;

    private String email;

    private String phone;

    /**
     * 用户余额
     */
    private Long balance;

    /**
     * 用户评分总分：满分5分
     */
    private Integer userscore;

    /**
     * 评分人数
     */
    private Integer scorenum;
}