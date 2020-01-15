package com.robin.usedbookmarketbackend.model;

import lombok.Data;

@Data
public class ShoppingCart {
    /**
     * 购物车ID
     */
    private Integer shoppingcartid;

    /**
     * 所属用户ID
     */
    private Integer userid;

    /**
     * 书籍ID
     */
    private Integer bookid;

    /**
     * 购买数量
     */
    private Integer purchasenum;
}