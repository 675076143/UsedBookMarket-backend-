package com.robin.usedbookmarketbackend.service;

import java.util.List;
import java.util.Set;

public interface UserRoleService {
    Set<String> getUserRoleByUserId(int userID);

    void updateUserRoleByUserID(int userID, List<Integer> roleID);
}
