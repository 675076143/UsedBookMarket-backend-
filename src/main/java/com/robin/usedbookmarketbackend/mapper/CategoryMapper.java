package com.robin.usedbookmarketbackend.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.robin.usedbookmarketbackend.model.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<String> findCategoryname();


}