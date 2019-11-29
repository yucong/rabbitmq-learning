package com.yucong.bm;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;




@Component
@RabbitListener(queues = {"delay.queue"})
public class DelayConsumer {
	
	/**
     * 短信通知
     */
	@RabbitHandler
	public void processSensitiveScan( String orderNo) {
		try {
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.print("开始发送短信------------->:" + orderNo + " " + sf.format(new Date()));
			//MandaoSmsUtil.send(msg.getPhone(), msg.getContent());
		} catch (Exception e) {
			System.out.print("延时消息消费失败::" + orderNo);
		}
	}
	
}
