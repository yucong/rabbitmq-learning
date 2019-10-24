package com.yucong;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.yucong.message.LogMessage;

@Component
@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value="${mq.config.delay.queue.info}",autoDelete="false"),
					exchange = @Exchange(value="${mq.config.delay.exchange}",type=ExchangeTypes.DIRECT),
					key = "${mq.config.delay.queue.info.routing.key}"
			)
		)
public class InfoDelayReceiver {

	@RabbitHandler
	public void process(LogMessage msg) {
		
		System.out.println("Delay Info........receiver: "+msg);
	
	}
	
}
