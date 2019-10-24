package com.yucong;

public class RabbitConsts {

	public static final String DEAD_EXCHANGE = "dead.exchange";
    public static final String DEAD_ROUTING_KEY = "dead-routing-key";
    public static final String ALL_ROUTING_KEY = "all";
    /**
     * 延迟队列 TTL 名称
     */
    public static final String DELAY_WAIT_QUEUE = "delay.wait.queue";

    /**
     * 消息消费的队列
     */
    public static final String DELAY_QUEUE = "delay.queue";
    public static final String DELAY_EXCHANGE = "delay.exchange";
    
    
    
    
    
    
/*    # 自定义配置。 配置交换器exchange、路由键routing-key、队列名称 queue name
    # 交换器名称
    mq.config.exchange=log.direct
    # info级别queue的名称
    mq.config.queue.info=log.info
    # info级别的路由键
    mq.config.queue.info.routing.key=log.info.routing.key

    # 交换器名称
    mq.config.delay.exchange=log.delay.direct
    # info级别queue的名称
    mq.config.delay.queue.info=log.delay.info
    # info级别的路由键
    mq.config.delay.queue.info.routing.key=log.delay.info.routing.key*/
    
}
