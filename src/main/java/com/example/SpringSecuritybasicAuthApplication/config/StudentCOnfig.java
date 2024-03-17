package com.example.SpringSecuritybasicAuthApplication.config;

import javax.sql.DataSource;
import org.springframework.security.config.Customizer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.example.SpringSecuritybasicAuthApplication.model.Student;
import com.example.SpringSecuritybasicAuthApplication.service.StudentUserDetailService;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;

@Configuration
@EnableWebSecurity
public class StudentCOnfig {

	@Autowired
	private DataSource dataSource;

//	@Autowired
//	private StudentUserDetailService ussDetailService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new StudentUserDetailService();
	}

//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        return  http
//	                .csrf(AbstractHttpConfigurer::disable)
//	                .authorizeHttpRequests(auth -> auth.requestMatchers("/register").permitAll() //вход без авторизации
//	                        .requestMatchers("/login").hasAuthority("Admin").anyRequest().authenticated()) //с авторизацией и аутентификацией
//	               
//	                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//	                .build();
//	    }
	


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   ///http.removeConfigurer(DefaultLoginPageConfigurer.class);

		 http.formLogin().disable().
				csrf().disable().authorizeHttpRequests(auth -> {
			auth.requestMatchers("/register").permitAll();
			auth.requestMatchers("/userLlogin").hasRole("User").anyRequest().authenticated();

		}).httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized for unauthorized requests
	}

//	@Bean
//	public UserDetailsManager users(HttpSecurity http) throws Exception {
//		AuthenticationManagerBuilder authenticationManagerBuilder = http
//				.getSharedObject(AuthenticationManagerBuilder.class);
//		authenticationManagerBuilder.userDetailsService(userDetailsService());
//
//		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
//		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
//		return jdbcUserDetailsManager;
//	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

}
