package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Import({ CommonConfiguration.class, ConfigProperties.class })
@EnableKafka
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(DemoApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		Producer producer = context.getBean(Producer.class);
		producer.send("hello");
		context.getBean(Consumer.class).latch.await(10, TimeUnit.SECONDS);
		context.close();
	}

}
