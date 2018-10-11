package com.ecofriend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecofriend.model.Provider;
import com.ecofriend.model.SiteUser;
import com.ecofriend.service.ProviderService;
import com.ecofriend.service.SiteUserService;

@Controller
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private SiteUserService siteUserService;

	/**
	 * get user name from security
	 * 
	 * @return
	 */
	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	// update get
	@RequestMapping(value = "/update_provider", method = RequestMethod.GET)
	public ModelAndView updateSender(ModelAndView modelAndView) {
		Provider provider = new Provider();

		modelAndView.getModel().put("provider", provider);
		modelAndView.setViewName("app.update_provider");
		return modelAndView;
	}

	// update post save
	@RequestMapping(value = "/update_provider", method = RequestMethod.POST)
	public ModelAndView updateSender(ModelAndView modelAndView, @Valid Provider provider, BindingResult result) {

		modelAndView.setViewName("app.update_provider");

		if (!result.hasErrors()) {
			String email = getUserName();
			SiteUser siteUser = siteUserService.findUserByEmail(email);
			provider.setSiteUser(siteUser);

			providerService.updateProvider(provider);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}
}
