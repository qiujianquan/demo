package com.example.demo.common;
import lombok.Getter;

import java.util.EnumSet;

/**
 * 状态码枚举
 */
@Getter
public enum ResponseMessageEnum  {

    /**
     * 调用成功与未知错误
     */
    ERROR(9999, "ERROR", "未知错误"),
    INVOKE_SUCCESS(200, "INVOKE_SUCCESS", "调用成功"),




    ;

    private int code;
    private String status;
    private String message;

    ResponseMessageEnum() {
    }

    ResponseMessageEnum(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public static ResponseMessageEnum getResultEnum(int code) {
        for (ResponseMessageEnum type : ResponseMessageEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return ERROR;
    }

    public static  ResponseMessageEnum getResultEnum(String message) {
        for ( ResponseMessageEnum type :  ResponseMessageEnum.values()) {
            if (type.getMessage().equals(message)) {
                return type;
            }
        }
        return ERROR;
    }


    public static int findByName(String errMessage) {
        for ( ResponseMessageEnum e : EnumSet.allOf( ResponseMessageEnum.class)) {
            if (e.message.equals(errMessage)) {
                return e.code;
            }
        }
        return 999999;
    }


}
