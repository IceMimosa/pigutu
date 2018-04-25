package com.pigutu.app;

import com.pigutu.app.utils.PigutuBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PigutuApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PigutuApplication.class);
		application.setBanner(new PigutuBanner());
		application.run(args);
	}
}
