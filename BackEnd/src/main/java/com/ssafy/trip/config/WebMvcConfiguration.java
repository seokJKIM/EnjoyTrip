package com.ssafy.trip.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@MapperScan(basePackages = {"com.ssafy.trip.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	private final List<String> patterns = Arrays.asList("/board/*","/place/*","/user/changePass","/user/delete");
	
	private ConfirmInterceptor confirmInterceptor;
	
	public WebMvcConfiguration(ConfirmInterceptor confirmInterceptor) {
		this.confirmInterceptor = confirmInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
	}
}
