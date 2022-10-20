package com.bilgeadam.SpringBootRestHibernate.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@RestController
@RequestMapping(path = "beans")
// sadece test profilinde ayağa kalksın
// @Profile(value = "test")
public class Beans
{
	@Autowired
	public ApplicationContext applicationContext;

	@Bean
	@Profile(value = "test")
	public OpenAPI springShopOpenAPITest()
	{
		return new OpenAPI().info(new Info().title("OBS - Öğrenci Bilgi Sistemi (TEST)").description("OBS application").version("V1.0").license(new License().name("Apache 2.0").url("localhost:8080/license"))).externalDocs(new ExternalDocumentation().description("OBS rest dökümantasyonu").url("localhost:8080/external"));
	}

	@Bean
	@Profile(value = "prod")
	public OpenAPI springShopOpenAPIProd()
	{
		return new OpenAPI().info(new Info().title("OBS - Öğrenci Bilgi Sistemi (PROD)").description("OBS application").version("V1.0").license(new License().name("Apache 2.0").url("localhost:8080/license"))).externalDocs(new ExternalDocumentation().description("OBS rest dökümantasyonu").url("localhost:8080/external"));
	}

	@GetMapping
	public ResponseEntity<String> getBeans()
	{
		// localhost:8080/beans
		String[] names = applicationContext.getBeanDefinitionNames();
		Arrays.sort(names);
		StringBuilder builder = new StringBuilder();
		builder.append("----- " + names.length + " -----").append("<br>");
		for (String name : names)
		{
			builder.append(name + " ---> " + applicationContext.getBean(name).getClass().getName()).append("<br>");
		}
		return ResponseEntity.ok(builder.toString());
	}
}
