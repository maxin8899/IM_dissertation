package com.model;

import lombok.Data;

/**
 * Created by haizhi on 2017/2/7.
 */
@Data
public class User {
    private Integer id;
    private Long mobile;
    private String password;
    private String name;
    private String avatar;
    public String toString(){
        return "[User]:{id:"+id+",mobile"+mobile+"}";
    }
}
