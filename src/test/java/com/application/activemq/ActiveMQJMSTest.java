package com.application.activemq;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.application.base.message.activemq.jms.customer.ActiveMqJmsMessageCustomer;
import com.application.base.message.activemq.jms.producer.ActiveMqJmsMessageProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.base.core.BaseJunit4Test;

public class ActiveMQJMSTest extends BaseJunit4Test {

	// 队列消息生产者
	@Autowired
	private ActiveMqJmsMessageProducer jmsProducer;

	// 队列消息消费者
	@Autowired
	private ActiveMqJmsMessageCustomer jmsCustomer;

	private String destionQueue = "jms.activeMQ";
	
	@Test
	public void send() {
		String msg = "jms-往消息队列中注入消息";
		for (int i = 0; i < 20; i++) {
			logger.info(msg+i);
			jmsProducer.sendMessage(msg+i,destionQueue,false,50000);
		}
	}
	
	@Test
	public void receive() {
		List<TextMessage> messages = jmsCustomer.receiveMessage(destionQueue);
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
