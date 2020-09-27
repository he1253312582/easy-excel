package com.heq.comp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 两种方式都可以,这里用接口来实现
 */
@Slf4j
@Component
//public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandlerImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS,PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Cache-Control", "No-cache");
        response.setHeader("Pragma", "No-cache");
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        if (exception instanceof LockedException) {
            map.put("message", "账户被锁定,登录失败!");
        } else if (exception instanceof BadCredentialsException) {
            map.put("message", "账户或密码输入错误,登录失败!");
        } else if (exception instanceof DisabledException) {
            map.put("message", "该账户已被禁用,登录失败!");
        } else if (exception instanceof AccountExpiredException) {
            map.put("message", "账户已经过期,登录失败!");
        } else if (exception instanceof CredentialsExpiredException) {
            map.put("message", "密码已过期,登录失败!");
        } else {
            map.put("message", "未知错误,登录失败!");
        }
        logger.error(map.toString());
        //response.sendRedirect("/login");

//        response.getWriter().write(objectMapper.writeValueAsString(map));

    }
}
