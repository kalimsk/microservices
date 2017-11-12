package com.cts.service.authserver;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cts.service.authserver.authBuilder.AuthenticationType;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
	AuthenticationType authType;
	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
				//.antMatchers("/oauth/**").permitAll()
				//.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				//.anyRequest()
				//.authenticated()
				//.and()
				//.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		authType.setAuthenticationManager(auth);		
	}
	
	 @Override
     public void configure(WebSecurity web) throws Exception {
       web.ignoring()
         .antMatchers(HttpMethod.OPTIONS);
     }
	
	 
}
