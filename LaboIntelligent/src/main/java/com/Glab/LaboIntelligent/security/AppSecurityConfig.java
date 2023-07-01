package com.Glab.LaboIntelligent.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;


 
import  com.Glab.LaboIntelligent.security.AccessDeniedExceptionHandler;




@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private EmployeeAuthenticationSuccessHandler successHandler;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
//Pour spécifier la service qui cherche l'user dans la BD 
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new AccessDeniedExceptionHandler();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		
		//http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).accessDeniedPage("/accessDenied.html");
		// Config for Login Form
		http.authorizeRequests().antMatchers("/assets/**").permitAll().and().formLogin()//
				// Submit URL of login page.
		.loginProcessingUrl("/loginProcessing") // Submit URL
				//.loginPage("/login")//
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error=true")//
				//.usernameParameter("email")//
				
				// Config for Logout Page
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

		
		http.authorizeRequests()
		//.anyRequest().authenticated()
		.and().formLogin().successHandler(successHandler).loginPage("/login").usernameParameter("email")
		.passwordParameter("password").permitAll()
		.and().httpBasic();
		
		http.authorizeRequests(). antMatchers("/**").hasAuthority("Admin");
		http.authorizeRequests(). antMatchers("/Etudiant/**").hasAuthority("Etudiant");
	http.authorizeRequests(). antMatchers("/Professeur/**").hasAuthority("Professeur");

		
		

		
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/InitialiserURL/**");
		web.ignoring().antMatchers("/InitialiserMP/**/**/");
		}
	/*
	 * @Bean public PersistentTokenRepository persistentTokenRepository1() {
	 * JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	 * db.setDataSource(dataSource); return db; }
	 * 
	 * // Token stored in Memory (Of Web Server).
	 * 
	 * @Bean public PersistentTokenRepository persistentTokenRepository() {
	 * InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	 * return memory; }
	 */

}
