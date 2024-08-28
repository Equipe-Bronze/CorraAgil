package br.com.pipocaagil.CorraAgil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class CorraAgilApplication {
	public static void main(String[] args) {
		SpringApplication.run(CorraAgilApplication.class, args);
	}
}
