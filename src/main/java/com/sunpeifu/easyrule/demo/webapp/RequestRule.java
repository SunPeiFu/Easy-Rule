package com.sunpeifu.easyrule.demo.webapp;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者:  sunpeifu
 * 日期:  2020/8/18
 * 描述:  请求规则
 */
@Rule
public class RequestRule {

    /**
     * 定义请求头中的常量
     */
    private static final String TOKEN = "TOKEN";


    /**
     * 定义条件
     */
    @Condition
    public boolean isRight(@Fact("request") HttpServletRequest request) {
        return request.getParameter(TOKEN) != null;
    }

    /**
     * 定义执行
     */
    @Action
    public void setToken(@Fact("request") HttpServletRequest request) {
        request.setAttribute(TOKEN,true);
    }
}
