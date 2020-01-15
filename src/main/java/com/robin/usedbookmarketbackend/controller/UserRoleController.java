package com.robin.usedbookmarketbackend.controller;

import com.robin.reactmarket.config.Response;
import com.robin.reactmarket.dto.userRole.DtoUpdateUserRoles;
import com.robin.reactmarket.dto.userRole.DtoUpdateUserRolesByRoleName;
import com.robin.reactmarket.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PutMapping("/userRoles")
    public Response updateUserRoles(@RequestBody @Validated DtoUpdateUserRoles request){
        try {
            int userID = request.getUserID();
            List<Integer> roleID = request.getRoleID();
            userRoleService.updateUserRoleByUserID(userID,roleID);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("400","failed",e.toString());
        }

    }

    @PutMapping("/userRolesInRoleName")
    public Response updateUserRolesInRoleName(@RequestBody @Validated
                                                      DtoUpdateUserRolesByRoleName request){
        try {
            int userID = request.getUserID();
            List<String> roleName = request.getRoleName();
            userRoleService.updateUserRoleByUserIDInRoleName(userID,roleName);
            return new Response("200","success",null);
        }catch (Exception e){
            return new Response("400","failed",e.toString());
        }

    }
}
