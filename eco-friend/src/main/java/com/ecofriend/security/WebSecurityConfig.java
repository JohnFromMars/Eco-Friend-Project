package com.ecofriend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecofriend.service.SiteUserService;

/**
 * Security configuration
 * 
 * @author user
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Configure the access roles for resources
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.authorizeRequests()
		  		// permit all user
				.antMatchers("/").permitAll()
				.antMatchers("/about").permitAll()
				.antMatchers("/js/*","/css/*","/img/*").permitAll()
				.antMatchers("/register").permitAll()
				
				//only permitted for role of provider
				.antMatchers("/update_provider",
							 "/order",
							 "/provider_order",
							 "/confirm_order").hasRole("PROVIDER")
				
				//only permitted for role of sender
				.antMatchers("/update_sender",
							 "/sender_order",
							 "/find_order",
							 "/pick_order").hasRole("SENDER")
				
				//only permitted for role of depot
				.antMatchers("/update_depot",
							 "/search_order",
							 "/search_result",
							 "/register_incentive").hasRole("DEPOT")
				
			// deny other access	
			.anyRequest().denyAll().and()
				
			
			//login page configuration
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
				
			.logout().permitAll();
			
								
		// @formatter:on
	}

	/**
	 * Set an in memory account only for test
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rock13810@gmail.com").password("12345").roles("USER");
	}

	/**
	 * Use password encoder to encrypt the password when user resister the web
	 * site
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(siteUserService).passwordEncoder(passwordEncoder);
	}

}
