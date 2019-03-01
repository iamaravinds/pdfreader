package com.avin.pdfReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PdfReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfReaderApplication.class, args);
	}

}
