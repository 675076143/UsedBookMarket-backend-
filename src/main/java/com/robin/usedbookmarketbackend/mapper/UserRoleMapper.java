package com.robin.usedbookmarketbackend.mapper;

import com.robin.usedbookmarketbackend.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;
@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("roleid") Integer roleid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int deleteByUserid(@Param("userid")Integer userid);

    Set<String> getRoleNameByUserID(@Param("userID") int userID);
}