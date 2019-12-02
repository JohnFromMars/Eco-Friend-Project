package com.ecofriend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecofriend.model.SiteUser;
import com.ecofriend.service.SiteUserService;

/**
 * Authentication controller
 * 
 * @author user
 *
 */
@Controller
public class AuthController {

	@Autowired
	private SiteUserService siteUserService;

	/**
	 * Login page request
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "app.login";
	}

	/**
	 * Register page get request
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(ModelAndView modelAndView) {

		// create and pass the site user object to the views
		SiteUser user = new SiteUser();

		modelAndView.getModel().put("user", user);
		modelAndView.setViewName("app.register");

		return modelAndView;
	}

	/**
	 * Register post request, insert the user into database
	 * 
	 * @param modelAndView
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(ModelAndView modelAndView, @Valid SiteUser user, BindingResult result) {

		modelAndView.setViewName("app.register");

		if (!result.hasErrors()) {

			// register service
			siteUserService.register(user);

			// direct to :/
			switch (user.getRole()) {
			case ROLE_DEPOT:
				modelAndView.setViewName("redirect:/");
				break;

			case ROLE_SENDER:
				modelAndView.setViewName("redirect:/");
				break;

			case ROLE_PROVIDER:
				modelAndView.setViewName("redirect:/");
				break;

			default:
				modelAndView.setViewName("redirect:/");
				break;
			}

		}

		return modelAndView;
	}

}
