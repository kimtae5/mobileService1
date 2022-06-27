package com.kt5.mobileservice1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kt5.mobileservice1.aop.MyInterceptor;

@Component
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	//인터셉터 등록하는 메서드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
	}

}
