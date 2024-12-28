package com.synccreativelabs.email_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@SpringBootApplication
public class EmailSvcApplication {

	public static void main(String[] args) {


		SpringApplication.run(EmailSvcApplication.class, args);
	}

}
