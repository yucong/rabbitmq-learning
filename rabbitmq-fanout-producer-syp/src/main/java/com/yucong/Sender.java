package com.yucong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送者
 * fanout交换器 - 
 *  使用fanout交换器的时候，交换器是忽略routing-key的匹配。
 *  因为广播不需要考虑路由键的匹配，只考虑在Exchange上绑定了多少个queue。
 *  会将接受到的消息发送到所有的绑定的queue中，进行消息的缓存。
 */
@Component
public class Sender {

	private Logger logger = LoggerFactory.getLogger(Sender.class);
	
	@Autowired
	private AmqpTemplate rabbitAmqpTemplate;
	
	//exchange 交换器名称
	//@Value("${mq.config.exchange}")
	private String exchange;
	
	/*
	 * 发送消息的方法
	 */
	public void send(String msg){
		//向消息队列发送消息
		//参数一：交换器名称。
		//参数二：路由键
		//参数三：消息
		this.rabbitAmqpTemplate.convertAndSend(this.exchange,"A", msg);
	}
	
	
    public void sendWelfareEndTime(String msg, Integer intervalTime) {
    	rabbitAmqpTemplate.convertAndSend("DELAY.WELFARE.EXCHANGE2", "", msg, message -> {
            logger.info("发送消息：{},时长：{}", msg, intervalTime);
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            // 毫秒为单位，指定此消息的延时时长
            message.getMessageProperties().setDelay(intervalTime);
            return message;
        });
    	
    }
}
