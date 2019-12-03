package com.liaozh.springboot.demo.model.dto.basedto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;

public class ResponseDto<T> {
    /**
     * 状态码
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    @ApiModelProperty("参数体data")
    @Valid
    private T data;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
