package com.yucong;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 和direct交换器的区别是： Exchange的类型为TOPIC。
 * 全日志处理。
 */
@Component
@RabbitListener(
			bindings=@QueueBinding(
					value=@Queue(value="${mq.config.queue.logs}",autoDelete="true"),
					exchange=@Exchange(value="${mq.config.exchange}",type=ExchangeTypes.TOPIC),
					key="*.log.*"
			)
		)
public class LogsReceiver {
	@RabbitHandler
	public void process(String msg){
		System.out.println("......All........receiver: "+msg);
	}
}
