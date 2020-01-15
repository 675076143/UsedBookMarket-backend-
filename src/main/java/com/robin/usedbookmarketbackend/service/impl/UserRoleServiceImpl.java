package com.robin.usedbookmarketbackend.service.impl;

import com.robin.usedbookmarketbackend.mapper.RoleMapper;
import com.robin.usedbookmarketbackend.mapper.UserRoleMapper;
import com.robin.usedbookmarketbackend.model.UserRole;
import com.robin.usedbookmarketbackend.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getUserRoleByUserId(int userID) {
        return userRoleMapper.getRoleNameByUserID(userID);
    }

    @Override
    public void updateUserRoleByUserID(int userID, List<Integer> roleID){
        userRoleMapper.deleteByUserid(userID);
        for(int i=0;i<roleID.size();i++){
            UserRole userRole = new UserRole(userID,roleID.get(i));
            userRoleMapper.insert(userRole);
        }
    }
}
