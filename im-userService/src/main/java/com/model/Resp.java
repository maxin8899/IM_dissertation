package com.model;

import lombok.Data;

/**
 * Created by haizhi on 2017/2/7.
 */
@Data
public class Resp {
    private Integer status;
    private String message;


    public Resp(String message){
        this.setStatus(0);
        this.setMessage(message);
    }
}
