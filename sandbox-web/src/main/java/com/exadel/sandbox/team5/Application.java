package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.barcodes.QRCode;
import com.exadel.sandbox.team5.util.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class Application {

	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

	@Bean
	public QRCode qrCodeGenerator() {
		return new QRCode();
	}

	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
