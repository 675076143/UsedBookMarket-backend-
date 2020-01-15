package com.robin.usedbookmarketbackend.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Ordersub {
    /**
    * 子订单ID
    */
    private Integer ordersubid;

    /**
    * 所属订单ID
    */
    private Integer orderid;

    /**
    * 书籍ID
    */
    private Integer bookname;

    /**
    * 书籍数量
    */
    private Integer ordernum;

    /**
    * 书籍价格
    */
    private BigDecimal price;
}