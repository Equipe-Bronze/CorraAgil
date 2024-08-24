package br.com.pipocaagil.CorraAgil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CorraAgilApplication {
	public static void main(String[] args) {
		SpringApplication.run(CorraAgilApplication.class, args);
	}
}
