package com.robin.usedbookmarketbackend.service.impl;

import com.robin.usedbookmarketbackend.dto.book.DtoBook;
import com.robin.usedbookmarketbackend.mapper.BookMapper;
import com.robin.usedbookmarketbackend.mapper.CategoryMapper;
import com.robin.usedbookmarketbackend.model.Book;
import com.robin.usedbookmarketbackend.service.BookSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookSerivce {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<DtoBook> getBooksByCategory(String categoryName) {
        List<DtoBook> dtoBookList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        //如果没有分类信息，则查询所有书籍
        if(categoryName == null){
            bookList = bookMapper.findAll();
        }else {
            bookList = bookMapper.findByCategoryname(categoryName);
        }
        for (Book book:bookList){
            DtoBook dtoBook = new DtoBook();
            dtoBook.setBookId(book.getBookid());
            dtoBook.setBookName(book.getBookname());
            dtoBook.setBookPrice(book.getPrice());
            dtoBook.setBookImg(book.getImage());
            dtoBookList.add(dtoBook);
        }
        return dtoBookList;
    }

    @Override
    public Book getDetailById(int bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public int addSellingBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public int deleteSellingBook(int bookId) {
        return bookMapper.deleteByPrimaryKey(bookId);
    }

    @Override
    public int modifySellingBook(Book book) {
        return bookMapper.updateByPrimaryKey(book);
    }
}
