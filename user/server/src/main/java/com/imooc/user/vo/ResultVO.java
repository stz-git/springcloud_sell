package com.imooc.user.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<E> implements Serializable {

    private Integer code;
    private String msg;
    private E data;
}
