package com.robin.usedbookmarketbackend.dto.userRole;

import lombok.Data;

import java.util.List;

@Data
public class DtoUpdateUserRoles {
    private Integer userID;
    private List<Integer> roleID;
}
