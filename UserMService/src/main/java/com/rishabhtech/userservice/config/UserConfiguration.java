package com.rishabhtech.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserConfiguration {

	@Bean
	public RestTemplate restTemplateBean()
	{
		return new RestTemplate();
	}
}
