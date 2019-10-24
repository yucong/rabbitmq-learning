package com.yucong;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.yucong.message.LogMessage;




@Component
@RabbitListener(queues = {"delay.queue"})
public class DelayConsumer {
	
	/**
     * 短信通知
     */
	@RabbitHandler
	public void processSensitiveScan(LogMessage msg) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("开始消费消息：" + sf.format(new Date()));
			System.out.print("msg：------------->:" + msg );
		} catch (Exception e) {
			System.out.print("延时消息消费失败::" + msg);
		}
	}
	
}
