package com.example.demo.common;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * controller中统一返回ApayResponse
 */

@Data
public class DemoResponse<T> {
    @ApiModelProperty(value = "状态")
    private Integer code    = 200;
    @ApiModelProperty(value = "信息")
    private String  message = "调用成功";
    @ApiModelProperty(value = "数据")
    private Object  data;
    private T       model;

    @Tolerate
    public DemoResponse() {
        this.code = code;
        this.message = message;
        this.data = new JSONObject();
    }

    /***成功返回时*/
    public DemoResponse(Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /***错误返回时*/
    public DemoResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = new JSONObject();
    }

    /***错误返回时*/
    public DemoResponse(String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private DemoResponse(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    /**
     * 设置 code 和 msg
     *
     * @param responseMessageEnum
     */
    public void setCodeAndMsg(ResponseMessageEnum responseMessageEnum) {
        this.code = responseMessageEnum.getCode();
        this.message = responseMessageEnum.getMessage();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer code;
        private String  message;
        private Object  data;

        public Builder() {
            this.code = ResponseMessageEnum.INVOKE_SUCCESS.getCode();
            this.message = ResponseMessageEnum.INVOKE_SUCCESS.getMessage();
            this.data = new JSONObject();
        }

        public Builder codeAndMsg(ResponseMessageEnum responseMessageEnum) {
            this.code = responseMessageEnum.getCode();
            this.message = responseMessageEnum.getMessage();
            return this;
        }

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.message = msg;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public DemoResponse build() {
            return new DemoResponse(this);
        }
    }
}
