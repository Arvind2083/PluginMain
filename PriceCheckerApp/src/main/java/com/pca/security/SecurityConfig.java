package com.pca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
				.password("admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/admin", "/admin").hasRole("ADMIN").antMatchers("/user", "/user")
				.hasRole("USER").antMatchers("/showAll", "/showAll").hasRole("USER").and().formLogin()
				.loginPage("/login").and().logout().permitAll().and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/login").and().csrf().disable();
		http.exceptionHandling().accessDeniedPage("/error");
	}

}
