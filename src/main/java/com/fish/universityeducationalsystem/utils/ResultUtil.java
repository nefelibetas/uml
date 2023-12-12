package com.fish.universityeducationalsystem.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fish.universityeducationalsystem.common.Result;
import com.fish.universityeducationalsystem.exception.ServiceException;
import com.fish.universityeducationalsystem.exception.ServiceExceptionEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ResultUtil {
    static final ObjectMapper mapper = new ObjectMapper();
    public static <T> Result<T> success() {
        return new Result<>(200, "success");
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }
    public static <T> Result<T> failure(ServiceException exception) {
        return new Result<>(exception);
    }
    public static <T> Result<T> failure(ServiceExceptionEnum exceptionEnum) {
        return new Result<>(exceptionEnum);
    }
    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message);
    }
    public static void failure(HttpServletResponse response, ServiceExceptionEnum exceptionEnum) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Result<Object> result = failure(exceptionEnum);
        mapper.writeValue(response.getOutputStream(), result);
    }
}
