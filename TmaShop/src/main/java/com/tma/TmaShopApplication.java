package com.tma;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.tma.config.StorageProperties;
import com.tma.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TmaShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmaShopApplication.class, args);
	}
@Bean
CommandLineRunner init (StorageService storageService) {
	return (args -> {
		storageService.init();
	});
}

}
