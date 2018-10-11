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

import com.ecofriend.model.Depot;
import com.ecofriend.model.SiteUser;
import com.ecofriend.service.DepotService;
import com.ecofriend.service.SiteUserService;

@Controller
public class DepotController {

	@Autowired
	private DepotService depotService;
	
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
	@RequestMapping(value = "/update_depot", method = RequestMethod.GET)
	public ModelAndView updateSender(ModelAndView modelAndView) {
		Depot depot = new Depot();

		modelAndView.getModel().put("depot", depot);
		modelAndView.setViewName("app.update_depot");
		return modelAndView;
	}

	// update post save
	@RequestMapping(value = "/update_depot", method = RequestMethod.POST)
	public ModelAndView updateSender(ModelAndView modelAndView, @Valid Depot depot, BindingResult result) {

		modelAndView.setViewName("app.update_depot");

		if (!result.hasErrors()) {
			String email = getUserName();
			SiteUser siteUser = siteUserService.findUserByEmail(email);
			depot.setSiteUser(siteUser);

			depotService.updateDepot(depot);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}

}
