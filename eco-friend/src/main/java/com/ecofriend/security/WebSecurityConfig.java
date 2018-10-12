package com.ecofriend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecofriend.service.SiteUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.authorizeRequests()
		  		// permit all user
				.antMatchers("/").permitAll()
				.antMatchers("/about").permitAll()
				.antMatchers("/js/*","/css/*","/img/*").permitAll()
				.antMatchers("/register").permitAll()
				
				.antMatchers("/update_provider",
							 "/order",
							 "/provider_order",
							 "/confirm_order").hasRole("PROVIDER")
				
				.antMatchers("/update_sender",
							 "/sender_order").hasRole("SENDER")
				
				.antMatchers("/update_depot").hasRole("DEPOT")
				
			.anyRequest().denyAll().and()
				
			
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
				
			.logout().permitAll();
			
								
		// @formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rock13810@gmail.com").password("12345").roles("USER");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(siteUserService).passwordEncoder(passwordEncoder);
	}

}
