package com.robin.usedbookmarketbackend.controller;

import com.robin.reactmarket.config.Response;
import com.robin.reactmarket.dto.secondaryCategory.DtoUpdateSecondaryCategoryByTopID;
import com.robin.reactmarket.service.SecondaryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SecondaryCategoryController {

    @Autowired(required = false)
    private SecondaryCategoryService secondaryCategoryService;

    @GetMapping("/secondaryCategories/{topCategoryID}")
    public Response getSecondaryCategoryByTopCategoryID(@PathVariable("topCategoryID") int topCategoryID){
        try {
            return new Response("200","success",secondaryCategoryService.getSecondaryCategory(topCategoryID));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @GetMapping("/secondaryCategory/{secondaryCategoryID}")
    public Response getSecondaryCategoryBySecondaryCategoryID(@PathVariable("secondaryCategoryID")int secondaryCategoryID){
        try {
            return new Response("200","success",secondaryCategoryService.getSecondaryCategoryBySecondaryCategoryID(secondaryCategoryID));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @GetMapping("/categoryNames/{secondaryCategoryID}")
    public Response getCategoryName(@PathVariable("secondaryCategoryID") int secondaryCategoryID){
        try {
            return new Response("200","success",secondaryCategoryService.getCategoryName(secondaryCategoryID));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @PostMapping("/secondaryCategory/{secondaryCategoryName}")
    public Response addSecondaryCategoryByTopID(@PathVariable("secondaryCategoryName") String secondaryCategoryName, int topCategoryID){
        try {
            return new Response("200","success",secondaryCategoryService
                    .addSecondaryCategory(secondaryCategoryName,topCategoryID));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @PutMapping("/secondaryCategory/{secondaryCategoryID}")
    public Response updateSecondaryCategoryByTopID(@PathVariable("secondaryCategoryID") int secondaryCategoryID,
                                                   @RequestBody @Validated DtoUpdateSecondaryCategoryByTopID data){
        try {
            String secondaryCategoryName = data.getSecondaryCategoryName();
            return new Response("200","success",secondaryCategoryService
                    .updateSecondaryCategory(secondaryCategoryID,secondaryCategoryName));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

    @DeleteMapping("/secondaryCategory/{secondaryCategoryID}")
    public Response deleteSecondaryCategoryByTopID(@PathVariable("secondaryCategoryID") int secondaryCategoryID){
        try {
            return new Response("200","success",secondaryCategoryService.deleteSecondaryCategory(secondaryCategoryID));
        }catch (Exception e){
            return new Response("400",e.toString(),null);
        }
    }

}
