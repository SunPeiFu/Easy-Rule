package com.sunpeifu.easyrule.demo.weather;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

/**
 * 作者:  sunpeifu
 * 日期:  2020/8/18
 * 描述:  官方示例
 */

/**
 * name 为规则唯一标识 , priority 为优先级,
 */
@Rule(name = "weather rule", description = "if it rain then take an umbrella" ,priority = 1)
public class WeatherRule {

    /**
     * 注解Conditon标注在返回布尔类型的方法上
     */
    @Condition
    public boolean itRains(@Fact("rain") boolean rain) {
        return false;
    }

    /**
     * 注解Action即Conditon返回为true的时候,执行的工作
     */
    @Action
    public void takeAnUmbrella() {
        System.out.println("take an umbrella");
    }

    public static void main(String[] args) {

        // 1 定义事实全集
        Facts facts = new Facts();
        // 2 设置注解@Fact对应的属性
        facts.put("rain", true);
        // 3 定义规则
        org.jeasy.rules.api.Rule weatherRule = new RuleBuilder()
                .name("weather rule")
                .description("是否带umbrella")
                .when(fact -> fact.get("rain").equals(true))
                .then(fact -> System.out.println("take umbrella")).build();
        // 4 定义规则总集
        Rules rules = new Rules();
        rules.register(weatherRule);
        // 5 实例化规则引擎
        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        // 6 点火触发
        defaultRulesEngine.fire(rules, facts);

    }

}
