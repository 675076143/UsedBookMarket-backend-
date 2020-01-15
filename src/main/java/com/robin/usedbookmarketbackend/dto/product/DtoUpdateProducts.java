package com.robin.usedbookmarketbackend.dto.product;

import lombok.Data;

@Data
public class DtoUpdateProducts {
    private String productName;
    private String productDesc;
    private double price;
    private int secondaryCategoryID;
    private String detail;
    private int status;
    private String image;
}
