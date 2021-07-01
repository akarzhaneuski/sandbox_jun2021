package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.barcodes.QRCode;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

	@Bean
	public QRCode qrCodeGenerator() {
		return new QRCode();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
