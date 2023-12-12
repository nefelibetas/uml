package com.fish.universityeducationalsystem.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String servletPath = request.getServletPath();
        String remoteAddr = request.getRemoteAddr();
        StringBuffer buffer = getStringBuffer(request, method);
        log.info("接收到{}请求: {}, 来自： {}", method, servletPath, remoteAddr);
        if (StringUtils.hasLength(buffer.toString()))
            log.info("参数: {}", buffer);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    private static StringBuffer getStringBuffer(HttpServletRequest request, String method) throws IOException {
        StringBuffer buffer = new StringBuffer();
        switch (method) {
            case "GET" -> {
                Map<String, String[]> parameterMap = request.getParameterMap();
                parameterMap.forEach((key, value) -> {
                    if (!Objects.isNull(key) && !Objects.isNull(value)) {
                        buffer.append("key: ");
                        buffer.append(key);
                        buffer.append(",value: ");
                        buffer.append(Arrays.toString(value));
                        buffer.append(" ");
                    }
                });
            }
            case "POST" -> {
                BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String tmp;
                while ((tmp = reader.readLine()) != null) {
                    buffer.append(tmp.trim());
                }
            }
        }
        return buffer;
    }
}
