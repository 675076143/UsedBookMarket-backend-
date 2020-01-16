package com.robin.usedbookmarketbackend.controller;

import com.robin.usedbookmarketbackend.config.Response;
import com.robin.usedbookmarketbackend.model.Book;
import com.robin.usedbookmarketbackend.service.BookSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookSerivce bookSerivce;

    @GetMapping("books")
    public Response getBooks(String categoryName){
        try {
            return new Response("200","success",bookSerivce.getBooksByCategory(categoryName));
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",e.toString());
        }
    }

    @GetMapping("bookDetails")
    public Response getBookDetails(int bookId){
        try {
            return new Response("200","success",bookSerivce.getDetailById(bookId));
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",e.toString());
        }
    }

    @PostMapping("books")
    public Response addSellingBook(@RequestBody Book book){
        try {
            return new Response("200","success",bookSerivce.addSellingBook(book));
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",e.toString());
        }
    }

    @DeleteMapping("books")
    public Response deleteSellingBook(int bookId){
        try {
            return new Response("200","success",bookSerivce.deleteSellingBook(bookId));
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",e.toString());
        }
    }

    @PutMapping("books")
    public Response modifySellingBook(@RequestBody Book book){
        try {
            return new Response("200","success",bookSerivce.modifySellingBook(book));
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",e.toString());
        }
    }
}
