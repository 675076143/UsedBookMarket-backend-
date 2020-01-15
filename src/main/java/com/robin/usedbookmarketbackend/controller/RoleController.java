package com.robin.usedbookmarketbackend.controller;

import com.robin.reactmarket.config.Response;
import com.robin.reactmarket.config.RoleResponse;
import com.robin.reactmarket.dto.role.DtoAuthRole;
import com.robin.reactmarket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public Response getAllRoles(){
        try {
            List<RoleResponse> data = roleService.getAllRoles();
            return new Response("200","success",data);
        }catch (Exception e){
            return new Response("400","failed",e.toString());
        }
    }

    @GetMapping("/roles/{roleID}")
    public Response getRoleByRoleID(@PathVariable("roleID")int roleID){
        try {
            RoleResponse data = roleService.getRoleByRoleID(roleID);
            return new Response("200","success",data);
        }catch (Exception e){
            return new Response("400","failed",e.toString());
        }
    }

    @PutMapping("/rolePermissions/{roleID}")
    public Response updateRolePermission(@PathVariable("roleID")int roleID,
                                         @RequestBody @Validated DtoAuthRole data){
        try {
            //前端接收数组,将数组转为list
            //调用更新的service
            roleService.updateRolePermissionByRoleID(roleID,data.getPermissionNames(),data.getAuthName());
            //如果更新成功,返回该角色的角色信息和权限信息
            RoleResponse responseData = roleService.getRoleByRoleID(roleID);
            return new Response("200","success",responseData);
        }catch (Exception e){
            e.printStackTrace();
            return new Response("400","failed",e.toString());
        }
    }

    @PostMapping("/roles")
    public Response addRole(String roleName){
        try {
            roleService.addRole(roleName);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("200","failed",null);
        }
    }
}
