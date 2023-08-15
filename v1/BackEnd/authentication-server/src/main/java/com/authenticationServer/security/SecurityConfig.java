package com.authenticationServer.security;

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

import com.authenticationServer.security.filters.AuthenticationFilter;
import com.authenticationServer.service.UserService;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The users service. */
	@Autowired
	private UserService usersService;
	
	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	/**
	 * Configures the security filter chain.
	 *
	 * @param http the HttpSecurity object
	 * @return the SecurityFilterChain object
	 * @throws Exception if an error occurs during configuration
	 */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    	
    	
    	// Configure AuthenticationManagerBuilder
    	AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	
    	authenticationManagerBuilder.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    	
    	AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    	
    	// Create AuthenticationFilter
    	AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService, environment, authenticationManager);
    	authenticationFilter.setFilterProcessesUrl("/authentication");
  
        http
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .authorizeHttpRequests()
        .antMatchers(HttpMethod.POST, "/authentication").permitAll()
        .antMatchers(HttpMethod.POST, "/ResetPasswordTools/**").permitAll()
        .antMatchers(HttpMethod.GET, "/ResetPasswordTools/**").permitAll()
        .antMatchers(HttpMethod.POST, "/administration/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/administration/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/administration/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/administration/**").hasRole("ADMIN")
        .and()
        .addFilter(authenticationFilter)
        .authenticationManager(authenticationManager)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
        return http.build();

    }

}
