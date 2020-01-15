package com.robin.usedbookmarketbackend.service.impl;

import com.robin.usedbookmarketbackend.config.UserLoginResponse;
import com.robin.usedbookmarketbackend.config.UserResponse;
import com.robin.usedbookmarketbackend.mapper.RoleMapper;
import com.robin.usedbookmarketbackend.mapper.UserMapper;
import com.robin.usedbookmarketbackend.model.User;
import com.robin.usedbookmarketbackend.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 将用户信息转换为响应格式的用户信息
     * @param user
     * @param roleName
     * @return
     */
    public UserResponse userToUserResponse(User user, List<String> roleName){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserID(user.getUserid());
        userResponse.setUserName(user.getUsername());
        userResponse.setUserState(user.getUserstate());
        userResponse.setCreateTime(user.getCreateTime());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRoleName(roleName);
        return userResponse;
    }

    public UserLoginResponse userToLoginResponse(User user, Set<String> permissionName,String token){
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setUserID(user.getUserid());
        userLoginResponse.setUserName(user.getUsername());
        userLoginResponse.setUserState(user.getUserstate());
        userLoginResponse.setCreateTime(user.getCreateTime());
        userLoginResponse.setEmail(user.getEmail());
        userLoginResponse.setPhone(user.getPhone());
        userLoginResponse.setPermissionName(permissionName);
        userLoginResponse.setToken(token);
        return userLoginResponse;
    }


    @Override
    public User getUserByUserName(String userName) {
        User user = userMapper.findFirstByUsername(userName);
        return user;
    }

    @Override
    public UserResponse findUserByUserName(String userName) {
        User user = userMapper.findFirstByUsername(userName);
        int userID= user.getUserid();
        //根据用户ID取得用户角色
        List<String> roleName = roleMapper.getRoleNameByUserID(userID);
        return userToUserResponse(user,roleName);
    }

    @Override
    public UserLoginResponse findUserPermission(String userName,String token){
        User user = userMapper.findFirstByUsername(userName);
        int userID= user.getUserid();
        //根据用户ID取得用户角色
        List<String> roleName = roleMapper.getRoleNameByUserID(userID);
        //根据角色名称取得权限(为了不重复,用set)
        Set<String> permissionNames = new HashSet<>();
        return userToLoginResponse(user,null,token);
    }

    @Override
    public List<UserResponse> findUsers() {
        List<UserResponse> userResponseList = new ArrayList();
        List<User> userList = userMapper.findAll();
        //将userList 转换为 userResponseList
        for(int i=0; i<userList.size(); i++){
            User user = userList.get(i);
            int userID= user.getUserid();
            //根据用户ID取得用户角色
            List<String> roleName = roleMapper.getRoleNameByUserID(userID);
            UserResponse userResponse = userToUserResponse(user,roleName);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    public UserResponse addUser(String userName, String email, String phone) throws Exception {
        int userState = 1;
        String salt = UUID.randomUUID().toString().replace("-","");
        //加密
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME,"123456",salt,512);
        String password = simpleHash.toString();
        Date createTime = new Date();
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setUserstate(userState);
        user.setSalt(salt);
        user.setCreateTime(createTime);
        user.setEmail(email);
        user.setPhone(phone);
        int effectRows = userMapper.insert(user);
        if(effectRows==1){
            return findUserByUserName(userName);
        }else {
            throw new Exception();
        }

    }
    @Override
    public UserResponse registerUser(String userName, String password, String email, String phone) throws Exception {
        int userState = 1;
        String salt = UUID.randomUUID().toString().replace("-","");
        //加密
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME,password,salt,512);
        Date createTime = new Date();
        User user = new User();
        user.setUsername(userName);
        user.setPassword(simpleHash.toString());
        user.setUserstate(userState);
        user.setCreateTime(createTime);
        user.setEmail(email);
        user.setPhone(phone);
        int effectRows = userMapper.insert(user);
        if(effectRows==1){
            return findUserByUserName(userName);
        }else {
            throw new Exception();
        }

    }

    @Override
    public void deleteUserByID(int userID) {
        userMapper.deleteByPrimaryKey(userID);
    }

    @Override
    public UserResponse updateUserByID(int userID, String email, String phone)
            throws Exception {
        User user = new User();
        user.setUserid(userID);
        user.setEmail(email);
        user.setPhone(phone);
        int effectRow = userMapper.updateByPrimaryKeySelective(user);
        if(effectRow==1){
            User userReturn = userMapper.selectByPrimaryKey(userID);
            List<String> roleName = roleMapper.getRoleNameByUserID(userID);
            return userToUserResponse(userReturn,roleName);
        }else {
            throw new Exception();
        }

    }

}
