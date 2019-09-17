package com.yucong;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class WelfareEndTimeOutConsumer {
	
    private Logger logger = LoggerFactory.getLogger(WelfareEndTimeOutConsumer.class);
    
    @RabbitListener(queues = RabbitWelfareConfig.Timeout_Welfare_Queue_Name)
    public void process(String tradeCode, Message message, Channel channel) throws IOException {
        try {
        	System.out.println("开始执行福利的抽奖...............:" + tradeCode);
        	
            logger.info("开始执行福利的抽奖,tradeCode:" + tradeCode);
            
            
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            logger.error("福利抽奖失败:{}", e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
    
}
