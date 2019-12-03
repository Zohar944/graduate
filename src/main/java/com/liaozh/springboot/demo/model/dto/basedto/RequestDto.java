package com.liaozh.springboot.demo.model.dto.basedto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class RequestDto<T> {

    @ApiModelProperty("参数体data")
    @Valid
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RequestDto(@Valid T data) {
        this.data = data;
    }
}
