package com.robin.usedbookmarketbackend.dto.userRole;

import lombok.Data;

import java.util.List;

@Data
public class DtoUpdateUserRolesByRoleName {
    private int userID;
    private List<String> roleName;
}
