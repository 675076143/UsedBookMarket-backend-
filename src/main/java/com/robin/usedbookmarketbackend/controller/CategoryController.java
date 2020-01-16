package com.robin.usedbookmarketbackend.controller;

import com.robin.usedbookmarketbackend.config.Response;
import com.robin.usedbookmarketbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public Response getAllCategories(){
        try {
            return new Response("200","success",categoryService.getAllCategories());
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",e.toString());
        }
    }
}
