package com.task3.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//rutas publicas
		registry.addMapping("/**")
			.allowedOrigins("/seria la url del frontend pero no tengo")
			.allowedMethods("GET","POST","DELETE","PUT","PATCH","OPTIONS")
			.allowedHeaders("Origin","Content-Type","Accept","Authorization")
			.allowCredentials(true)
			.maxAge(3600);
		//rutas privadas
		registry.addMapping("/auth/**")
			.allowedOrigins("/seria la url del frontend pero no tengo")
			.allowedMethods("GET","POST","DELETE","PUT","PATCH","OPTIONS")
			.allowedHeaders("Origin","Content-Type","Accept","Authorization")
			.allowCredentials(false)
			.maxAge(3600);
	}

}
