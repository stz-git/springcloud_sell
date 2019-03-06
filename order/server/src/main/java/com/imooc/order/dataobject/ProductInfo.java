package com.imooc.order.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class ProductInfo {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
