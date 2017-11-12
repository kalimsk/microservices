package com.cts.service.rsc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring()
        .antMatchers(HttpMethod.OPTIONS);
    }
	
	 
}
