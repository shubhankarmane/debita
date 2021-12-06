package com.shubhankar.debita;

import com.shubhankar.debita.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DebitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebitaApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> authFilterFilterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> authFilterFilterRegistrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		authFilterFilterRegistrationBean.setFilter(authFilter);
		authFilterFilterRegistrationBean.addUrlPatterns("/api/category/*");
		authFilterFilterRegistrationBean.addUrlPatterns("/api/transaction/*");
		return authFilterFilterRegistrationBean;
	}
}
