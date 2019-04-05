package br.com.teste.listener;

import static  br.com.teste.config.JmsConfig.ORDER_QUEUE;

import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.teste.service.SpreadsheetProductFacade;

@Component
public class Consumer {
	
	private static Logger log = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	private SpreadsheetProductFacade facade;

	@JmsListener(destination=ORDER_QUEUE)
	public void receiveMessage(@Payload String file, @Headers MessageHeaders headers, Message message, Session session) throws Exception {
		
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("- - - - - - - - -Message Received- - - - - -  -");
		
		log.info("received <" + file + ">");

		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("######          Message Details           #####");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("headers: " + headers);
		log.info("message: " + message);
		log.info("session: " + session);
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		
		
		facade.execute(file, session.hashCode());
		
		
	}
	

}
