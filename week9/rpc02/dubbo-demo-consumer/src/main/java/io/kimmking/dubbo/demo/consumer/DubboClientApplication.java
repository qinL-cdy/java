package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.TransactionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DubboClientApplication {

	@DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
	private TransactionService transactionService;

	public static void main(String[] args) {

		SpringApplication.run(DubboClientApplication.class).close();

	}

	@Bean
	public ApplicationRunner runner() {
		return args -> {
			transactionService.transaction();
		};
	}

}
