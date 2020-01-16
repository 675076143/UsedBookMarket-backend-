package com.robin.usedbookmarketbackend.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * 订单id
     */
    private Integer orderid;

    /**
     * 所属用户ID
     */
    private Integer userid;

    /**
     * 订单时间
     */
    private Date ordertime;

    /**
     * 总金额
     */
    private BigDecimal totalprice;
}