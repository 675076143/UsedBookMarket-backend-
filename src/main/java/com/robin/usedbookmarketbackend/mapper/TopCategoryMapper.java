package com.robin.usedbookmarketbackend.mapper;

import com.robin.usedbookmarketbackend.model.TopCategory;

public interface TopCategoryMapper {
    int deleteByPrimaryKey(Integer topCategoryid);

    int insert(TopCategory record);

    int insertSelective(TopCategory record);

    TopCategory selectByPrimaryKey(Integer topCategoryid);

    int updateByPrimaryKeySelective(TopCategory record);

    int updateByPrimaryKey(TopCategory record);
}