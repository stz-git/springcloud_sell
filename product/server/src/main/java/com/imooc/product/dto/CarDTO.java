package com.imooc.product.dto;

import lombok.Data;

@Data
public class CarDTO {
    private String productId;
    private Integer productQuantity;

    public CarDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CarDTO() {
    }
}
