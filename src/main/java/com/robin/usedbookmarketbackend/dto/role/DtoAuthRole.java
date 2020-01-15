package com.robin.usedbookmarketbackend.dto.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class DtoAuthRole {

    @NotBlank(message = "授权者不能为空")
    private String authName;

    //@Size(min = 1,message = "用户权限不能为空")
    private List<String> permissionNames;


}
