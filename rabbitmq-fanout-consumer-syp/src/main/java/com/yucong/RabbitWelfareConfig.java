package com.yucong;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitWelfareConfig {
    
	/**
     * 福利超时延时交换机
     */
    public static final String Delay_Welfare_Exchange_Name = "DELAY.WELFARE.EXCHANGE2";

    /**
     * 超时福利关闭队列
     */
    public static final String Timeout_Welfare_Queue_Name = "CLOSE_WELFARE_2";


    @Bean
    public Queue delayWelfareQueue() {
        return new Queue(RabbitWelfareConfig.Timeout_Welfare_Queue_Name, true);
    }


    /**
     * 定义广播模式的延时交换机 无需绑定路由
     *
     * @return
     */
    @Bean
    FanoutExchange delayWelfareExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        FanoutExchange topicExchange = new FanoutExchange(RabbitWelfareConfig.Delay_Welfare_Exchange_Name, true, false, args);
        topicExchange.setDelayed(true);
        return topicExchange;
    }

    /**
     * 绑定延时队列与交换机
     *
     * @return
     */
    @Bean
    public Binding delayWelfareBind() {
        return BindingBuilder.bind(delayWelfareQueue()).to(delayWelfareExchange());
    }


}
