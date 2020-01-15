package com.robin.usedbookmarketbackend.controller;

import com.robin.reactmarket.config.Response;
import com.robin.reactmarket.dto.topCategory.DtoUpdateTopCategory;
import com.robin.reactmarket.service.TopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TopCategoryController {

    @Autowired
    private TopCategoryService topCategoryService;

    @GetMapping("/topCategories/{id}")
    public Response getTopCategory(@PathVariable("id") int id){
        try {
            return new Response("200","success",topCategoryService.getTopCategory(id));
        }catch (Exception e){
            return new Response("400",e.toString(),null);

        }
    }

    @GetMapping("/topCategories")
    public Response getAllTopCategory(){
        try {
            return new Response("200","success",topCategoryService.getAllTopCategory());
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @PostMapping("/topCategories/{topCategoryName}")
    public Response addTopCategory(@PathVariable("topCategoryName")String topCategoryName){
        try {
            return new Response("200","success",topCategoryService.insertTopCategory(topCategoryName));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @DeleteMapping("/topCategories/{topCategoryID}")
    public Response deleteTopCategory(@PathVariable("topCategoryID")int topCategoryID){
        try {
            return new Response("200","success",topCategoryService.deleteTopCategory(topCategoryID));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @PutMapping("/topCategories/{topCategoryID}")
    public Response updateTopCategory(@PathVariable("topCategoryID")int topCategoryID,
                                      @RequestBody @Validated DtoUpdateTopCategory data){
        try {
            String topCategoryName = data.getTopCategoryName();
            topCategoryService.updateTopCategory(topCategoryID,topCategoryName);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

}
