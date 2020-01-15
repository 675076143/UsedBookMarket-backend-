package com.robin.usedbookmarketbackend.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private int roleID;
    private String roleName;
    private Date createTime;
    private Date authTime;
    private String authName;
    private List<String> rolePermissions;
}
