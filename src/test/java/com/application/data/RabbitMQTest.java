package com.application.data;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.application.base.core.BaseJunit4Test;
import com.application.base.message.springmq.handler.MessageHandler;
import com.application.base.message.springmq.msg.CommonMessage;
import com.application.base.message.springmq.sender.MessageSender;

public class RabbitMQTest extends BaseJunit4Test {

	@Autowired
	private MessageSender messageSender;  
	@Autowired
	private MessageHandler messageHandler;
	
    @Test  
    public void testSendMessage(){  
        CommonMessage message = new CommonMessage();  
        message.setSource("tonson");  
        JSONObject obj = new JSONObject();  
        obj.put("test", "test json message");  
        message.setMessage(obj);  
        messageSender.setRoutingKey("message.tonson");  
        messageSender.sendDataToQueue(message);  
    }  

    @Test  
    public void testgetMessage(){  
    	
    }  
}
