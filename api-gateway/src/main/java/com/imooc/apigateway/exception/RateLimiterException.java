package com.imooc.apigateway.exception;

public class RateLimiterException extends RuntimeException{

    private Integer code;

    public RateLimiterException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
