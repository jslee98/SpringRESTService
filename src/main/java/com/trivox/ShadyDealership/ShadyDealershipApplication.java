package com.trivox.ShadyDealership;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Jeff Lee
 * Basic Spring Framework for Web Service
 *
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan({"controller", "service"})
public class ShadyDealershipApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ShadyDealershipApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShadyDealershipApplication.class);
	}
}
