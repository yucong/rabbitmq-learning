package com.yucong;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.stereotype.Component;

import com.yucong.message.LogMessage;

@Component
@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value="${mq.config.queue.info}",autoDelete="false",
						arguments = { 
								@Argument(name = "x-dead-letter-exchange", value = "${mq.config.delay.exchange}"),
				                @Argument(name = "x-dead-letter-routing-key", value = "${mq.config.delay.queue.info}")
						} ),
					exchange = @Exchange(value="${mq.config.exchange}",type=ExchangeTypes.DIRECT),
					key = "${mq.config.queue.info.routing.key}"
			)
		)
public class InfoReceiver {

	@RabbitHandler
	public void process(LogMessage msg){
		System.out.println("Info........receiver: "+msg);
	}
}
