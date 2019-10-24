package com.yucong;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yucong.message.LogMessage;

/**
 * 消息发送者 - Producer。
 * Producer类型的对象，必须交由Spring容器管理。
 * 使用SpringBoot提供的AMQP启动器，来访问rabbitmq的时候，都是通过AmqpTemplate来实现的。
 * 如果全局配置文件中，配置了rabbitmq相关内容，且工程依赖了starter-amqp，则spring容器自动创建AmqpTemplate对象。
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitAmqpTemplate;

	
	// 延迟时长
	@Value("${mq.config.queue.delaytime}")
	private Integer delayTime;
	
	/*
	 * 发送消息的方法
	 */
	public void send(LogMessage msg){
		/**
		 * convertAndSend - 转换并发送消息的template方法。
		 * 是将传入的普通java对象，转换为rabbitmq中需要的message类型对象，并发送消息到rabbitmq中。
		 * 参数一：交换器名称。 类型是String
		 * 参数二：路由键。 类型是String
		 * 参数三：消息，是要发送的消息内容对象。类型是Object
		 */
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("生产消息时间：------------->:" + sf.format(new Date()));
		System.out.println("延时时长：------------->:" + delayTime );
		this.rabbitAmqpTemplate.convertAndSend(RabbitConsts.DEAD_EXCHANGE, RabbitConsts.DEAD_ROUTING_KEY, msg, message -> {
            message.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return message;
        });
	}
	
	
}
