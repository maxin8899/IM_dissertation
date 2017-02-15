package com.model;

import org.springframework.http.HttpStatus;

/**
 * Created by haizhi on 2017/2/8.
 */
public enum ErrorCode {
    REPLICIA_USER(10001, "该用户已注册", HttpStatus.BAD_REQUEST),
    WRONG_PARAMETER(10002,"参数有误",HttpStatus.BAD_REQUEST),
    NO_USER(10003,"用户不存在",HttpStatus.UNAUTHORIZED),
    WRONG_PASSWORD(10004,"密码错误",HttpStatus.UNAUTHORIZED),
    FILE_UPLOAD_ERROR(10005,"文件上传失败",HttpStatus.BAD_REQUEST);

    private Integer status;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int status, String message,HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public static ErrorCode getByStatus(Integer status){
        for (ErrorCode c : ErrorCode.values()) {
            if (c.getStatus() == status) {
                return c;
            }
        }
        return null;
    }

}
