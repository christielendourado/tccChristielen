package com.christielen.integrativo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class IntegrativoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrativoApplication.class, args);
	}

	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jackson2JsonMessageConverter());
		return template;
	}
}
