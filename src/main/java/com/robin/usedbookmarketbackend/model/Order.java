package com.robin.usedbookmarketbackend.model;

import lombok.Data;

@Data
public class Order {
    /**
    * 订单id
    */
    private Integer orderid;

    /**
    * 所属用户ID
    */
    private Integer userid;
}