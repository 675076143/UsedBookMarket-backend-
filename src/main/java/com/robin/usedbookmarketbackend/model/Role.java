package com.robin.usedbookmarketbackend.model;

import java.util.Date;
import lombok.Data;

@Data
public class Role {
    private Integer roleid;

    private String rolename;

    private Date createTime;

    private Date authTime;

    private Integer authid;
}