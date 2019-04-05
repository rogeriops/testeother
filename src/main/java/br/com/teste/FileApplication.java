package br.com.teste;


import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import br.com.teste.property.FileStorageProperties;

/**
 * 
 * @author Rogerio
 * Inicializa a aplicação e ativa os recursos definidos em FileStorageproperties
 *
 */

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class FileApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
	
//	@Bean
//    public Queue queue() {
//        return new ActiveMQQueue("queue");
//    }

}
