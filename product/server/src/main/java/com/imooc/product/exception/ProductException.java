package com.imooc.product.exception;

import com.imooc.product.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ProductException extends RuntimeException{
    private Integer code;

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ProductException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
