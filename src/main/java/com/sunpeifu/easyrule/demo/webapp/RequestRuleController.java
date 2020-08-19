package com.sunpeifu.easyrule.demo.webapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作者:  sunpeifu
 * 日期:  2020/8/18
 * 描述:
 */
@RestController
public class RequestRuleController {

    @GetMapping("testWebApp")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (isSuspicious(request)) {
            out.print("Access denied");
        } else {
            out.print("Welcome!");
        }
    }

    private boolean isSuspicious(HttpServletRequest request) {
        return request.getAttribute("TOKEN") != null;
    }
}
