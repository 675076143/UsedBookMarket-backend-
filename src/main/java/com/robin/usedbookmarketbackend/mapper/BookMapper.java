package com.robin.usedbookmarketbackend.mapper;
import org.apache.ibatis.annotations.Param;

import com.robin.usedbookmarketbackend.model.Book;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Integer bookid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> findAll();

    List<Book> findByCategoryname(@Param("categoryName")String categoryName);


}