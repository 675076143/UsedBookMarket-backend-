package com.robin.usedbookmarketbackend.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBook {
    private Integer bookId;
    private String bookName;
   private BigDecimal bookPrice;
    private String bookImg;
}
