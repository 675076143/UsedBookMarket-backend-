package com.robin.usedbookmarketbackend.dto.product;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DtoUpdateProductsStatus {
    @NotNull(message = "状态码不能为空")
    int status;
}
