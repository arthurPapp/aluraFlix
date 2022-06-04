package br.com.alura.challanger.aluraFlix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableSwagger2WebFlux
@ComponentScan(basePackages = {"br.com.alura.challanger.controller",
								"br.com.alura.challanger.service",
								"br.com.alura.challanger.model","br.com.alura.challanger.configuration",
								"br.com.alura.challanger.validator.group"})
@EnableMongoRepositories(basePackages = { "br.com.alura.challanger.repository"})
public class AluraFlixApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraFlixApplication.class, args);
	}

}
