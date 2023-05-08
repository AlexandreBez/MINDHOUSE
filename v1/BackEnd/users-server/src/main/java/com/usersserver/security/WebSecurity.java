package com.usersserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.usersserver.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private UserService usersService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    	
    	// Configure AuthenticationManagerBuilder
    	AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	
    	authenticationManagerBuilder.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    	
    	AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    	
    	// Create AuthenticationFilter
    	AuthenticationFilter authenticationFilter = 
    			new AuthenticationFilter(usersService, environment, authenticationManager);
    	authenticationFilter.setFilterProcessesUrl("/users-server/v1/login");

        http.csrf().disable();
  
        http.authorizeHttpRequests()
        .antMatchers(HttpMethod.POST, "/users/v1/").hasRole("ADMIN")
        .and()
        .addFilter(authenticationFilter)
        .authenticationManager(authenticationManager)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
        return http.build();

    }
}
