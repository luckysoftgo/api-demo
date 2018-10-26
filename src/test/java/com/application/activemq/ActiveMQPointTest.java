package com.application.activemq;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.application.base.message.activemq.point.customer.ActiveMqPointMessageCustomer;
import com.application.base.message.activemq.point.producer.ActiveMqPointMessageProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.base.core.BaseJunit4Test;

public class ActiveMQPointTest extends BaseJunit4Test {

	// 队列消息生产者
	@Autowired
	private ActiveMqPointMessageProducer pointProducer;

	// 队列消息消费者
	@Autowired
	private ActiveMqPointMessageCustomer pointCustomer;

	private String destionQueue = "point.activeMQ";
	
	@Test
	public void send() {
		String msg = "point-往消息队列中注入消息";
		for (int i = 0; i < 20; i++) {
			logger.info(msg+i);
			pointProducer.sendMessage(msg+i,destionQueue,false,50000);
		}
	}
	
	@Test
	public void receive() {
		List<TextMessage> messages = pointCustomer.receiveMessage(destionQueue);
		try {
			for (TextMessage message : messages) {
				logger.info("====================================================================================================================");
				logger.info("接收到消息是:"+message.getText());
				logger.info("====================================================================================================================");
			}
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
