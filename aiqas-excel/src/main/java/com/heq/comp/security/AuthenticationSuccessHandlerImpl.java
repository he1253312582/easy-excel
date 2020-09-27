package com.heq.comp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功之后的处理机制
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @param request        请求参数
     * @param response       返回参数
     * @param authentication 权限校验的数据
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功!");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS,PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Cache-Control", "No-cache");
        response.setHeader("Pragma", "No-cache");
        response.setContentType("application/json;charset=UTF-8");

        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect("/controller/index");
//        response.sendRedirect("/swagger-ui.html");
//        response.getWriter().write(objectMapper.writeValueAsString(authentication));
    }


}
