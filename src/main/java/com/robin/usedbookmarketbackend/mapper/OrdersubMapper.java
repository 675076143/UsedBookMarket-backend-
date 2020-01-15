package com.robin.usedbookmarketbackend.mapper;

import com.robin.usedbookmarketbackend.model.Ordersub;

public interface OrdersubMapper {
    int deleteByPrimaryKey(Integer ordersubid);

    int insert(Ordersub record);

    int insertSelective(Ordersub record);

    Ordersub selectByPrimaryKey(Integer ordersubid);

    int updateByPrimaryKeySelective(Ordersub record);

    int updateByPrimaryKey(Ordersub record);
}