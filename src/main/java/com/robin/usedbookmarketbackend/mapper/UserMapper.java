package com.robin.usedbookmarketbackend.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.robin.usedbookmarketbackend.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findFirstByUsername(@Param("username")String username);

    List<User> findAll();


}