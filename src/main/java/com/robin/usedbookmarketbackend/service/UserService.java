package com.robin.usedbookmarketbackend.service;

import com.robin.usedbookmarketbackend.config.UserLoginResponse;
import com.robin.usedbookmarketbackend.config.UserResponse;
import com.robin.usedbookmarketbackend.model.User;

import java.util.List;

public interface UserService {
    //获取单个用户所有信息
    User getUserByUserName(String userName);
    //获取单个用户的信息, 包括用户的角色信息
    UserResponse findUserByUserName(String userName);
    //获取用户登陆后的信息
    UserLoginResponse findUserPermission(String userName, String token);

    //获取所有用户的信息, 其中包括该用户的角色ID
    List<UserResponse> findUsers();
    //创建用户
    UserResponse addUser(String userName, String email, String phone) throws Exception;
    //注册用户
    UserResponse registerUser(String userName, String password, String email, String phone) throws Exception;
    void deleteUserByID(int userID);

    UserResponse updateUserByID(int userID, String email, String phone)throws Exception;
}
