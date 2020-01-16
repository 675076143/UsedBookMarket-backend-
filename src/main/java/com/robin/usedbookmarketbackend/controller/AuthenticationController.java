package com.robin.usedbookmarketbackend.controller;

import com.robin.usedbookmarketbackend.config.Response;
import com.robin.usedbookmarketbackend.config.UserLoginResponse;
import com.robin.usedbookmarketbackend.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response login(String userName,String password){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String sessionID =subject.getSession().getId().toString();
            HashMap<String,String> map = new HashMap<>();
            map.put("token",sessionID);
            //userService
            UserLoginResponse response = userService.findUserPermission(userName,sessionID);
            return new Response("200","登录成功",response);
        }catch (UnknownAccountException e){
            System.out.println(e.toString());
            return new Response("400","用户名错误","");
        }catch (IncorrectCredentialsException e){
            System.out.println(e.toString());
            return new Response("400","密码错误","");
        }catch (DisabledAccountException e){
            return new Response("400","此用户被禁用","");
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","未知错误,请联系管理员!","");
        }
    }
    @PostMapping("/register")
    public Response register(String userName,String password,String email,String phone){
        try {
            userService.registerUser(userName,password,email,phone);
            UserLoginResponse response = userService.findUserPermission(userName,"");
            return new Response("200","注册成功",response);
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","未知错误,请联系管理员!","");
        }
    }

    @GetMapping("/without_login")
    public Response without_login(){
        return new Response("400","您还为登录",null);
    }

    @GetMapping("/unauthorized")
    public Response unauthorized(){
        return new Response("401","没有权限!",null);
    }
}
