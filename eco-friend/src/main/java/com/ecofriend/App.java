package com.ecofriend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Eco-Friend web application entry
 * 
 * @author user
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * Basic view resolver
	 * 
	 * @return
	 */
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setViewClass(TilesView.class);
		return urlBasedViewResolver;
	}

	/**
	 * Apache tile setting
	 * 
	 * @return
	 */
	@Bean
	public TilesConfigurer TilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		String[] def = { "/WEB-INF/tiles.xml" };
		tilesConfigurer.setDefinitions(def);
		return tilesConfigurer;
	}

	/**
	 * Password encoder setting
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder gEncoder() {
		return new BCryptPasswordEncoder();
	}

}
