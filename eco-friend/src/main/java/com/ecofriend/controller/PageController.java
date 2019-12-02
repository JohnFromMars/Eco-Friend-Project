package com.ecofriend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * General page controller
 * 
 * @author user
 *
 */
@Controller
public class PageController {

	/**
	 * return home page
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String home() {
		return "app.home";
	}

	/**
	 * return about page
	 * 
	 * @return
	 */
	@RequestMapping("/about")
	public String about() {
		return "app.about";
	}
}
