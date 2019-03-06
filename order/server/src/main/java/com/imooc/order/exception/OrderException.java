package com.imooc.order.exception;

import com.imooc.order.enums.ResultEnum;
import lombok.Getter;

@Getter
public class OrderException extends RuntimeException{
    private Integer code;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public OrderException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
