package com.Glab.LaboIntelligent.security;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//j'ai mis uniquement @EnableSecurity et j'ai laissé @configuration car il n ya pas de @Bean ici
@EnableWebSecurity
public class WebMvcConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
	
    	registry.addViewController("/").setViewName("pages-login");
    	registry.addViewController("/login").setViewName("pages-login");
        registry.addViewController("/index").setViewName("pages-login");

	}

}

