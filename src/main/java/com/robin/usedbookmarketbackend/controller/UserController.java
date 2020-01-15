package com.robin.usedbookmarketbackend.controller;

import com.robin.reactmarket.config.Response;
import com.robin.reactmarket.config.UserResponse;
import com.robin.reactmarket.dto.user.DtoUpdateUser;
import com.robin.reactmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/{userName}")
    public Response createUser(@PathVariable("userName") String userName, String email, String phone){
        try {
            UserResponse user = userService.addUser(userName,email,phone);
            return new Response("200","success", user);
        }catch (Exception e){
            return new Response("400","failed",null);
        }
    }
    @GetMapping("/users/{userName}")
    public Response getUser(@PathVariable("userName") String userName){
        try {
            if (userService.getUserByUserName(userName)!=null){
                return new Response("200","success",userService.findUserByUserName(userName));
            }else {

                return new Response("400","用户不存在!",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",null);
        }

    }
    @GetMapping("/users")
    public Response getAllUsers(){
        try {
            List<UserResponse> userList = userService.findUsers();
            return new Response("200","success", userList);
        }catch (Exception e){
            return new Response("400","failed",null);
        }
    }
    @PutMapping("/users/{userID}")
    public Response updateUser(@PathVariable("userID")int userID,
                               @RequestBody @Validated DtoUpdateUser requestData){
        try{
            String email = requestData.getEmail();
            String phone = requestData.getPhone();
            UserResponse responseData = userService.updateUserByID(userID,email,phone);
            return new Response("200","success",responseData);
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",null);
        }
    }
    @DeleteMapping("/users/{userID}")
    public Response deleteUser(@PathVariable("userID")int userID){
        try {
            userService.deleteUserByID(userID);
            return new Response("200","success", null);
        }catch (Exception e){
            return new Response("400","failed",null);
        }
    }
}
