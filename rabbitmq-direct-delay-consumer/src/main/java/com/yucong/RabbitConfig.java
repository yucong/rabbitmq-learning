package com.yucong;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {


    /**
     * step2:
     * exchange交换机根据Routing-Key(routing-key)来
     * 投递到DELAY_PROCESS_QUEUE(delay.wait.queue)队列中
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitConsts.DEAD_EXCHANGE);
    }
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayWaitQueue())
        		.to(exchange())
        		.with(RabbitConsts.DEAD_ROUTING_KEY);
    }
	
    /**
     * step3:
     * 延时队列里面的消费无人消息，导致消息消费超时，
     * 会转发到DXL，并携带routing-key（all）
     */
    @Bean
    public Queue delayWaitQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", RabbitConsts.DELAY_EXCHANGE);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", RabbitConsts.ALL_ROUTING_KEY);
        return new Queue(RabbitConsts.DELAY_WAIT_QUEUE, true, false, false, params);
    }


    /**
     * step4：
     * 
     * delayExchange会根据ALL_ROUTING_KEY将消息投递到delayQueue,
     * 消息一投递到delayQueue中，就会被监听的消息者消费
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(RabbitConsts.DELAY_EXCHANGE);
    }
    @Bean
    public Binding delayBinding() {
        // 如果要让延迟队列之间有关联,这里的 routingKey 和 绑定的交换机很关键
        return BindingBuilder.bind(delayQueue())
        		.to(delayExchange())
        		.with(RabbitConsts.ALL_ROUTING_KEY);
    }
    @Bean
    public Queue delayQueue() {
        return new Queue(RabbitConsts.DELAY_QUEUE, true);
    }
	
	
}
