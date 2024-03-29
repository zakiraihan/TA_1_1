package com.apap.tugasAkhir.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * form untuk login berada di “/login” dan dapat diakses siapapun. 
	 * Selain pada “/login” perlu dilakukan autentikasi.
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
		.csrf().ignoringAntMatchers("/api/**").and()
		.authorizeRequests()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/api/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/", "/login", "/penanganan/**").hasAnyAuthority("Admin", "Dokter")
		.antMatchers("/daftar-request/**", "/obat/request", "/jadwal-jaga", "/kamar/**").hasAnyAuthority("Admin")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		.permitAll();
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

}
