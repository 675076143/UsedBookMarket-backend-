package com.robin.usedbookmarketbackend.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Book {
    /**
     * 书籍ID
     */
    private Integer bookid;

    /**
     * 书籍名称
     */
    private String bookname;

    private String bookdesc;

    private BigDecimal price;

    private Integer topcategoryid;

    private String detail;

    /**
     * 状态码：0上架，1下架，2已出售
     */
    private Integer status;

    private String image;

    /**
     * 所属用户
     */
    private Integer userid;

    /**
     * 书籍库存数量
     */
    private Integer bookstock;

    /**
     * 售出数量
     */
    private Integer soldnum;

    /**
     * 上架时间
     */
    private Date stackingtime;
}