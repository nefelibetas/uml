package com.fish.universityeducationalsystem.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ServiceException extends RuntimeException{
    String msg;
    int code;
    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.msg = serviceExceptionEnum.getMessage();
    }
}
