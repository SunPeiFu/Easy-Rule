package com.sunpeifu.easyrule.demo.webapp;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 作者:  daike
 * 日期:  2020/8/18
 * 描述:
 */
@Component
public class RequestRuleFilter implements Filter {

    /**
     * 定义全局成员
     */
    private Rules rules;
    private RulesEngine rulesEngine;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 实例化成员
        rulesEngine = new DefaultRulesEngine();
        rules = new Rules();
        rules.register(rulesEngine);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 1 定义事实
        Facts facts = new Facts();
        // 2 设置事实和条件
        facts.put("request", request);
        // 3 执行点火
        rulesEngine.fire(rules, facts);
        filterChain.doFilter(request, response);
    }
}
