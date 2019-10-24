package com.yucong;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class Sender2 {

	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	/*
	 * 发送消息的方法
	 */
	public void sendOrderNo(String orderNo){
		
		int delayTime = 30000;// 30s
		rabbitTemplate.convertAndSend(RabbitConsts.EXCHANGE, RabbitConsts.ROUTING_KEY, orderNo, message -> {
            message.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return message;
        });
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("开始生产短信消息--------->:" + orderNo + " " + sf.format(new Date()));
		
		
	}
	
	
}
