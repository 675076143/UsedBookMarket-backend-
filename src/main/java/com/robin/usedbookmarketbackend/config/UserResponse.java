package com.robin.usedbookmarketbackend.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userID;
    private String userName;
    private int userState;
    private Date createTime;
    private String email;
    private String phone;
    private List<String> roleName;
}
