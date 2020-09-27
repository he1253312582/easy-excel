package com.heq.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created user ： heqiang
 * created date : 2019/3/18 8:59
 * Description : No Description
 * version : 1.0
 */
@Controller
@RequestMapping("/controller")
public class JSPController {

    @RequestMapping("index")
    public String jspIndex(Map<String, Object> map) {
        map.put("param1", "value1");
        map.put("param2", "value2");
        map.put("param3", "value3");
        return "index";
    }

    /**
     * 跳转swagger页面
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/swagger-ui")
    public void swagger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    /**
     * 跳转到文件上传页面
     */
    @RequestMapping("/files")
    public String fileUploadJsp() {
        return "files";

    }


}
