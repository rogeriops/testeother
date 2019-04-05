package br.com.teste.listener;

import static  br.com.teste.config.JmsConfig.ORDER_QUEUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;



@Component
public class Sender {
	
	private static Logger log = LoggerFactory.getLogger(Sender.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;
 

 
    public void send(String message) {
    	log.info("sending to queue <" + message + ">");
        this.jmsTemplate.convertAndSend(ORDER_QUEUE, message);
    }

}
