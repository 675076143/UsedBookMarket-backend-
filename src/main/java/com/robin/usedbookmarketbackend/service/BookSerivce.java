package com.robin.usedbookmarketbackend.service;

import com.robin.usedbookmarketbackend.dto.book.DtoBook;
import com.robin.usedbookmarketbackend.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookSerivce {
    //分类获取书籍
    List<DtoBook> getBooksByCategory(String categoryName);
    //获取书详情信息
    Book getDetailById(int bookId);
    //上架书本
    int addSellingBook(Book book);
    //下架书本
    int deleteSellingBook(int bookId);
    //修改书本信息
    int modifySellingBook(Book book);
}
