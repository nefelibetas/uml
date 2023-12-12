package com.fish.universityeducationalsystem.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fish.universityeducationalsystem.exception.ServiceException;
import com.fish.universityeducationalsystem.exception.ServiceExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result <T> implements Serializable {
    int code;
    String message;
    T data;
    public Result(ServiceException exception) {
        this.code = exception.getCode();
        this.message = exception.getMsg();
        this.data = null;
    }
    public Result(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
        this.data = null;
    }
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
    public Result(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }
}
